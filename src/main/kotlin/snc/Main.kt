package snc

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Profile
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

@SpringBootApplication
class Main


@Component
@Profile("init")
class InitCommand : CommandLineRunner {
    override fun run(vararg args: String?) {
        println("run")
        println(javaClass.classLoader.getResource("foo.txt"))
        println(ClassPathResource("foo.txt"))
    }
}

fun main(args: Array<String>) {
    runApplication<Main>(*args)
}