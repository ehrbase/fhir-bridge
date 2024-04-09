---
name: Defect(Robot)
about: Report issues found by Robot tests
labels: bug
---



## Test Case/s To Reproduce Issue

# path to test case

fhir-bridge/tests/robot/...
fhir-bridge/tests/robot/...
fhir-bridge/tests/robot/...


# Using test data from:

fhir-bridge/src/test/resources/...
fhir-bridge/src/test/resources/...
fhir-bridge/src/test/resources/...


# by test case name (wildcards possible)

e.g.
008 Create Body Height
012 Create Body Weight
008 Create History of Travel (invalid/missing 'component[0] start date')


# robot command to execute related test case(s) in your terminal/console

robot -d results -L TRACE (PARAMETERS) robot


# Description



## Actual Result

foo

## Expected Result

bar