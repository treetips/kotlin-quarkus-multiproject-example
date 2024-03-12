package com.example.grpcserver.service

import com.example.grpcserver.grpc.Greeter
import com.example.grpcserver.grpc.HelloReply
import com.example.grpcserver.grpc.HelloRequest
import io.quarkus.grpc.GrpcService
import io.smallrye.mutiny.Uni

/**
 * Hello gRPC Service
 *
 * @author saekidaisuke
 */
@GrpcService
class HelloService : Greeter {
  override fun sayHello(request: HelloRequest): Uni<HelloReply> {
    return Uni.createFrom().item {
      HelloReply.newBuilder().setMessage("[gRPC Server] Hello " + request.name).build()
    }
  }
}
