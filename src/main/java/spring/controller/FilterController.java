package spring.controller;

import org.springframework.http.ResponseEntity;
import spring.model.IpFilterRuleModel;
import org.springframework.web.bind.annotation.*;
import spring.service.FilterService;

@RestController
@RequestMapping("/api/filter")
public class FilterController {

    private final FilterService service;

    public FilterController(FilterService service) {
        this.service = service;
    }

    @PostMapping("/addrule")
    public ResponseEntity<String> addRule(@RequestParam String id, @RequestBody IpFilterRuleModel rule) {
        return ResponseEntity.ok(service.addRule(id, rule));
    }

    @DeleteMapping("/removerule/{id}")
    public ResponseEntity<String> removeRule(@PathVariable String id) {
        return ResponseEntity.ok(service.removeRule(id));
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkIp(@RequestParam String sourceIp, @RequestParam String destinationIp) {
        return ResponseEntity.ok(service.checkIp(sourceIp, destinationIp));
    }
}
