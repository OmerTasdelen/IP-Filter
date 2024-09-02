package spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.entity.IpFilterRule;
import spring.service.IpFilterService;

@RestController
@RequestMapping("/api/ipfilter")
public class IpFilterController {

    private final IpFilterService service;

    public IpFilterController(IpFilterService service) {
        this.service = service;
    }

    @PostMapping("/addRule")
    public ResponseEntity<String> addRule(@RequestBody IpFilterRule rule) {
        return ResponseEntity.ok(service.addRule(rule));
    }

    @DeleteMapping("/deleteRule/{id}")
    public ResponseEntity<String> deleteRule(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteRule(id));
    }

    @GetMapping("/checkIp")
    public ResponseEntity<String> checkIp(@RequestParam String sourceIp, @RequestParam String destinationIp) {
        return ResponseEntity.ok(service.checkIp(sourceIp, destinationIp));
    }
}
