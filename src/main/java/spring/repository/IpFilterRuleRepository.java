package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.entity.IpFilterRule;

@Repository
public interface IpFilterRuleRepository extends JpaRepository<IpFilterRule, Long> {
}
