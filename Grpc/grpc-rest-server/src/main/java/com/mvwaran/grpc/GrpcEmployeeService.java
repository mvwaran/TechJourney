package com.mvwaran.grpc;

import com.mvwaran.common.Employee;
import com.mvwaran.common.EmployeeConfigData;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@GRpcService
public class GrpcEmployeeService extends ReactorEmployeeServiceGrpc.EmployeeServiceImplBase {

    @Autowired
    private EmployeeConfigData employeeConfigData;

    @Override
    public Mono<GrpcEmployeeListResponse> getAllEmployees(Mono<Empty> request) {
        GrpcEmployeeListResponse response = GrpcEmployeeListResponse.newBuilder()
                .addAllEmployees(employeeConfigData.getEmployees().stream()
                        .map(employeeFromDb -> GrpcEmployee.newBuilder()
                                .setEmployeeId(employeeFromDb.getId())
                                .setEmployeeName(employeeFromDb.getName())
                                .build())
                        .collect(Collectors.toList()))
                .build();
        return Mono.just(response);
    }
}
