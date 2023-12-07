package com.pivovarit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.pivovarit", importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitectureTest {

    @ArchTest
    private static ArchRule onlyPublicFacade = classes()
      .that()
      .arePublic()
      .and()
      .resideInAnyPackage("..rental")
      .should()
      .haveSimpleNameEndingWith("Facade");

    @ArchTest
    public static final ArchRule warehouseInternalClassesShouldBeAccessedByPackageOnly =
      classes()
        .that()
        .resideInAPackage("..warehouse..")
        .and()
        .arePublic()
        .and()
        .haveSimpleNameNotEndingWith("Facade")
        .should()
        .onlyHaveDependentClassesThat()
        .resideInAPackage("..warehouse..");
}
