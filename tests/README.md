# How to run this test

> Precondition: Python 3.7 (or above) & Pip are installed
> 
> Robot Framework and test dependencies are installed (`pip install -r requirements.txt)

1. start EHRbase (and it's required PostgreSQL DB) - for details check ehrbase repository*
2. start FHIRbridge (`java -jar target/fhir-bridge-1.0.0.jar`) - requires to `mvn package` it first*
3. execute robot test (s. command below)

```bash
robot -d results/ -L TRACE  robot/Observation                   # runs all tests under Observation
robot -d results/ -L TRACE -i create  robot                     # runs all tests with the tag "create"
robot -d results/ -L TRACE -i createANDobservation  robot       # runs all tests which have the tag create and observation (test needs both tags)
robot -d results/ -L TRACE -i createORobservation  robot        # runs all tests which have the tag create or observation (test needs at least one of the tag)
robot -d results/ -L TRACE -i condition -i observation  robot   # runs all tests with tag condition and all tests with tag observation (same as OR)

# command from CI
robot -d results/0 -L TRACE --skip TODO --skip future --skiponfailure not-ready robot
```

*alternatively you can start EHRbase + FHIRbridge with one command using docker-compose:
```bash
# start ehrbase and fhirbridge
cd ../docker
docker-compose -f docker-compose-light.yml up

# stop everything
CTRL + C
docker-compose down
```


```bash
# robot command line options explained

-d results
# ensures robot output files are save in ./results

-L TRACE
# ensures output log has all available details

-i Observation
# runs only the tests with the given tag, in this case "Observation".
```


# The 'not-ready' tag and it's sub-tags
To be more specific about why a TC is marked as `not-ready` we agreed on using special sub-tags that have to be provided in addition to a 'not-ready' tag. The naming convention for these sub-tags is as follows:
- `not-ready`    `not-implemented`
- `not-ready`    `bug`
- `not-ready`    `todo`
- `not-ready`    `test-issue` / `broken` / `test-broken` (?) 


# How to generate test documentation in HTML format
```bash
# docu command
python3 -m robot.testdoc robot results/fhir-bridge-robot-testdocu.html
```

# How to trigger Jenkins pipline to feed crr environments with test data
1. login into Jenkins (VPN required)
2. navigate to "Inject Test Data"
3. click "Build with Parameters"
4. select desired environment from dropdown menus
5. provide EHRbase Basic Auth username and password for selected environent
6. click "Build"
7. When tests pass, selected environment should contain FHIR resources and EHR records with Compositions
