package com.example.notiefytoplistserver;

import com.example.grpc.SongServiceProto;
import com.example.grpc.ValidationServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class ValidationService extends ValidationServiceGrpc.ValidationServiceImplBase {

    @Override
    public void validateAnalytics(SongServiceProto.SongCountList request, StreamObserver<SongServiceProto.AnalyticsResponse> responseObserver) {

        String message = "You have been listening these songs last month: %s".formatted(
                request.getSongCountsList().stream()
                        .map(songCount -> "%s [%s]".formatted(songCount.getName(), songCount.getPlaysCount()))
                        .collect(Collectors.joining(System.lineSeparator()))
        );

        responseObserver.onNext(SongServiceProto.AnalyticsResponse.newBuilder()
                .setSuccess(true)
                .setMessage(message)
                .build());

        responseObserver.onCompleted();
    }
}
