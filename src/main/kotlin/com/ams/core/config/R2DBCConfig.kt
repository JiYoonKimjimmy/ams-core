package com.ams.core.config

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

/**
 * [H2 R2DBC 설정 방법]
 * - properties r2dbc 설정 추가
 * - java configuration class r2dbc 설정 추가
 * [H2 Table auth DDL]
 * - `resources > create-ams-table.sql` SQL 파일 생성
 * - SQL 파일에 `CREATE` 문 추가
 * [H2 file lock 에러 방지 - (미동작)]
 * - H2 host url 옵션 설정 추가
 * - `;DB_CLOSE_ON_EXIT=TRUE;FILE_LOCK=NO`
 * - 위 옵션 추가하면 서버 구동 후 H2 console 접속 가능
 * - 하지만, H2 console 접속 후, API 요청하면 다시 `file lock` 에러 발생
 */
@EnableR2dbcRepositories
@Configuration
class R2DBCConfig : AbstractR2dbcConfiguration() {

    override fun connectionFactory(): ConnectionFactory =
        H2ConnectionConfiguration
            .builder()
            .file("~/h2/ams;DB_CLOSE_ON_EXIT=TRUE;FILE_LOCK=NO")
            .username("admin")
            .password("admin1234")
            .build()
            .let { H2ConnectionFactory(it) }

    @Bean
    fun transactionManager(connectionFactory: ConnectionFactory): ReactiveTransactionManager =
        R2dbcTransactionManager(connectionFactory)

    @Bean
    fun dbInitializer(): ConnectionFactoryInitializer =
        ConnectionFactoryInitializer()
            .apply {
                this.setConnectionFactory(connectionFactory())
                this.setDatabasePopulator(setDatabasePopulator())
            }

    private fun setDatabasePopulator(): ResourceDatabasePopulator =
        ResourceDatabasePopulator()
            .apply {
                this.addScript(ClassPathResource("create-ams-tables-h2.sql"))
                this.setContinueOnError(true)
            }

}