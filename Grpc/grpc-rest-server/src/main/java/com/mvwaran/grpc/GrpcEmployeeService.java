package com.mvwaran.grpc;

import com.mvwaran.common.Employee;
import com.mvwaran.common.EmployeeConfigData;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GRpcService
public class GrpcEmployeeService extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    @Autowired
    private EmployeeConfigData employeeConfigData;

    @Override
    public void getAllEmployees(Empty request, StreamObserver<GrpcEmployeeListResponse> responseObserver) {
        GrpcEmployeeListResponse response = GrpcEmployeeListResponse.newBuilder()
                .addAllEmployees(employeeConfigData.getEmployees().stream()
                        .map(employeeFromDb -> GrpcEmployee.newBuilder()
                                .setEmployeeId(employeeFromDb.getId())
                                .setEmployeeName(employeeFromDb.getName())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
