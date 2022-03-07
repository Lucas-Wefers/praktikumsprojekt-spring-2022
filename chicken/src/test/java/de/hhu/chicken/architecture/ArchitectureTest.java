package de.hhu.chicken.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.Configurations.consideringOnlyDependenciesInDiagram;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.adhereToPlantUmlDiagram;

import com.tngtech.archunit.core.importer.ImportOption;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import de.hhu.chicken.ChickenApplication;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@AnalyzeClasses(packagesOf = ChickenApplication.class, importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchitectureTest {
  
}
