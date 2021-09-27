package com.frete

import io.grpc.stub.StreamObserver
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import kotlin.random.Random

@Singleton
class FretesGrpcServer : FreteServiceGrpc.FreteServiceImplBase() {

    private val logger = LoggerFactory.getLogger(FretesGrpcServer::class.java)
    override fun calcula(request: FreteRequest?, responseObserver: StreamObserver<FreteResponse>?) {

        logger.info("Calculando frete para request: $request")

        val response = FreteResponse.newBuilder()
            .setCep(request!!.cep)
            .setValor(Random.nextDouble(from = 0.0, until = 140.0))
            .build()

        logger.info("Frete calculado: $response")
        responseObserver!!.onNext(response)
        responseObserver.onCompleted()

    }
}