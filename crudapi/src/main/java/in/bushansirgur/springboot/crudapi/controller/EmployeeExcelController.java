package in.bushansirgur.springboot.crudapi.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bushansirgur.springboot.crudapi.model.Employee;
import in.bushansirgur.springboot.crudapi.report.ExcelGeneratorReport;
import in.bushansirgur.springboot.crudapi.service.EmployeeService;
 
@RestController
@RequestMapping("/api/excel")
public class EmployeeExcelController {
	@Autowired
	private EmployeeService employeeService;
 
    @GetMapping(value = "/download/employee.xlsx")
    public ResponseEntity<InputStreamResource> excelEmployeeReport() throws IOException {
        List<Employee> employees = (List<Employee>) employeeService.get();
    
    ByteArrayInputStream in = ExcelGeneratorReport.employeeToExcel(employees);
    // return IOUtils.toByteArray(in);
    
    HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=employee.xlsx");
    
     return ResponseEntity
                  .ok()
                  .headers(headers)
                  .body(new InputStreamResource(in));
    }
}
 