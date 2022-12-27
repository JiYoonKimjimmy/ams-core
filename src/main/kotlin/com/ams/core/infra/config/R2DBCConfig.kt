package com.ams.core.infra.config

import io.r2dbc.h2.H2ConnectionConfiguration
import io.r2dbc.h2.H2ConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.transaction.ReactiveTransactionManager

@EnableConfigurationProperties(R2DBCProperties::class)
@EnableR2dbcRepositories
@Configuration
class R2DBCConfig(
    val r2dbcProperties: R2DBCProperties
) : AbstractR2dbcConfiguration() {

    @Bean
    override fun connectionFactory(): ConnectionFactory =
        H2ConnectionFactory(connectionConfiguration())

    @Bean
    fun dbInitializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer =
        ConnectionFactoryInitializer()
            .apply {
                this.setConnectionFactory(connectionFactory)
                this.setDatabasePopulator(setDatabasePopulator())
            }

    @Bean
    fun transactionManager(connectionFactory: ConnectionFactory): ReactiveTransactionManager =
        R2dbcTransactionManager(connectionFactory)

    private fun connectionConfiguration(): H2ConnectionConfiguration =
        H2ConnectionConfiguration
            .builder()
            .tcp(r2dbcProperties.host, r2dbcProperties.path)
            .username(r2dbcProperties.username)
            .password(r2dbcProperties.password)
            .build()

    private fun setDatabasePopulator(): ResourceDatabasePopulator =
        ResourceDatabasePopulator()
            .apply {
                this.addScript(ClassPathResource("query/create-ams-tables-h2.sql"))
                this.setContinueOnError(true)
            }

}
