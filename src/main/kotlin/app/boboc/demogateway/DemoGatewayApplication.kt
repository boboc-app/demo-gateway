package app.boboc.demogateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoGatewayApplication

fun main(args: Array<String>) {
    runApplication<DemoGatewayApplication>(*args)
}
