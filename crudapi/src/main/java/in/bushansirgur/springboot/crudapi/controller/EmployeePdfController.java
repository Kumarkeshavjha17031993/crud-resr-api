package in.bushansirgur.springboot.crudapi.controller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bushansirgur.springboot.crudapi.model.Employee;
import in.bushansirgur.springboot.crudapi.report.GeneratePdfReport;
import in.bushansirgur.springboot.crudapi.service.EmployeeService;


@RestController
@RequestMapping("/api/pdf")
public class EmployeePdfController {
	@Autowired
	private EmployeeService employeeService;

    @GetMapping(value = "/employee",
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> employeeReport() throws IOException {
        List<Employee> employees = (List<Employee>) employeeService.get();
 
        ByteArrayInputStream bis = GeneratePdfReport.employeeReport(employees);
 
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=employees.pdf");
 
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
