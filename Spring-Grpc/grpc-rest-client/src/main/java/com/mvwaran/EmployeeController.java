package com.mvwaran;

import com.mvwaran.grpc.EmployeeServiceGrpc;
import com.mvwaran.grpc.Empty;
import com.mvwaran.grpc.GrpcEmployeeListResponse;
import io.grpc.ManagedChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class EmployeeController {

    @Autowired
    private WebClient webClient;

    @Autowired
    private ManagedChannel managedChannel;

    @GetMapping("/grpc/employees/readAll")
    public List<Employee> readAllGrpc() {
        EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeServiceBlockingStub =
                EmployeeServiceGrpc.newBlockingStub(managedChannel);
        log.info("start of from grpc");
        GrpcEmployeeListResponse grpcEmployeeListResponse =
                employeeServiceBlockingStub.getAllEmployees(Empty.newBuilder().build());
        log.info("end of from grpc");
        return grpcEmployeeListResponse.getEmployeesList()
                .stream()
                .map(grpcEmployee -> Employee.builder()
                        .id(grpcEmployee.getEmployeeId())
                        .name(grpcEmployee.getEmployeeName())
                        .build())
                .collect(Collectors.toList());
    }

    @GetMapping("/rest/employees/readAll")
    public Flux<Employee> readAllRest() {
        return webClient
                .get()
                .uri("/employees/readAll")
                .retrieve()
                .bodyToFlux(Employee.class);
    }
}
