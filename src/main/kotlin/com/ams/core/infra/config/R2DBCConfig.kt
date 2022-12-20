package com.ams.core.infra.config

import io.r2dbc.h2.H2ConnectionConfiguration
import io.r2dbc.h2.H2ConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.transaction.ReactiveTransactionManager

@EnableR2dbcRepositories
@Configuration
class R2DBCConfig : AbstractR2dbcConfiguration() {

    @Bean
    override fun connectionFactory(): ConnectionFactory =
        H2ConnectionConfiguration
            .builder()
            .tcp("localhost", "~/h2/ams")
            .username("admin")
            .password("admin1234")
            .build()
            .let { H2ConnectionFactory(it) }

    @Bean
    fun transactionManager(connectionFactory: ConnectionFactory): ReactiveTransactionManager =
        R2dbcTransactionManager(connectionFactory)

    @Bean
    fun dbInitializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer =
        ConnectionFactoryInitializer()
            .apply {
                this.setConnectionFactory(connectionFactory())
                this.setDatabasePopulator(setDatabasePopulator())
            }

    private fun setDatabasePopulator(): ResourceDatabasePopulator =
        ResourceDatabasePopulator()
            .apply {
                this.addScript(ClassPathResource("query/create-ams-tables-h2.sql"))
                this.setContinueOnError(true)
            }

}
