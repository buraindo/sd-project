package ru.itmo.cafe

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import ru.itmo.cafe.cli.manager.CafeManager
import ru.itmo.cafe.profiler.configuration.ApplicationContextProvider
import ru.itmo.cafe.profiler.configuration.ContextConfiguration

fun main() {
    ApplicationContextProvider().setApplicationContext(AnnotationConfigApplicationContext(ContextConfiguration::class.java))
    CafeManager().run()
}