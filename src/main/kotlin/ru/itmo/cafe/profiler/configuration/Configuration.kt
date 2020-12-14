package ru.itmo.cafe.profiler.configuration

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import ru.itmo.cafe.cli.state.payment.PaymentState
import ru.itmo.cafe.profiler.aspect.Profiler

class ApplicationContextProvider : ApplicationContextAware {
    override fun setApplicationContext(ctx: ApplicationContext) {
        context = ctx
    }

    companion object {
        lateinit var context: ApplicationContext
    }
}

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
open class ContextConfiguration {
    @Bean
    open fun aspect() = Profiler

    @Bean
    open fun paymentState() = PaymentState()
}
