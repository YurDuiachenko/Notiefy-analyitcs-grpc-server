package com.example.notiefytoplistserver;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class NotiefyToplistServer {

	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(8080)
				.addService(new ValidationService())
				.build();

		server.start();
		System.out.println("Server started on port 8080");
		server.awaitTermination();
	}
}
