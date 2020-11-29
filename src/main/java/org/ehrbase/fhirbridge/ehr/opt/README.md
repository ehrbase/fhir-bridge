To create or recreate the generated DTOs 

- Have the client lib locally cloned and build
- Navigate to its target folder
- and run (your modified version of), for each OPT you want to generate a DTO from

``
java -jar client-library-0.3.0.jar -opt /$REPO_PATH/fhir-bridge/templates/$OPT_NAME.opt -out /$REPO_PATH/fhir-bridge/src/main/java -package org.ehrbase.fhirbridge.opt
``