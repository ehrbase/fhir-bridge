# How to run this test

> Precondition: Python 3.7 (or above) & Pip are installed
> 
> Robot Framework and test dependencies are installed (`pip install -r requirements.txt)

1. start EHRbase (and it's required PostgreSQL DB) - for details check ehrbase repository
2. start FHIRbridge (`java -jar target/fhir-bridge-1.0.0.jar`) - requires to `mvn package` it first
3. execute robot test (s. command below)

```bash
robot -d results/ -L TRACE  robot/Observation # runs all tests under Observation
robot -d results/ -L TRACE -i create  robot # runs all tests with the tag "create"
robot -d results/ -L TRACE -i createANDobservation  robot # runs all tests which have the tag create and observation (test needs both tags)
robot -d results/ -L TRACE -i createORobservation  robot # runs all tests which have the tag create or observation (test needs at least one of the tag)
robot -d results/ -L TRACE -i condition -i observation  robot # runs all tests with tag condition and all tests with tag observation (same as OR)
```


```bash
# robot command line options explained

-d results
# ensures robot output files are save in ./results

-L TRACE
# ensures output log has all available details

-i Observation
# runs only the tests with the given tag
```
