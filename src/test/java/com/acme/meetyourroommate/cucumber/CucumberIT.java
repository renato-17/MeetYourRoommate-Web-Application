package com.acme.meetyourroommate.cucumber;

import com.acme.meetyourroommate.MeetyourroommateApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;


@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest(classes = {MeetyourroommateApplication.class ,CucumberIT.class, TestConfiguration.class},webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber.json"},
        features = "classpath:features"
)
public class CucumberIT {

}