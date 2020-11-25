# Copyright (c) 2019 Wladislaw Wagner (Vitasystems GmbH), Peter Wohlfarth (Appsfactory GmbH),
# Dave Petzold (Appsfactory GmbH)
#
# This file is part of Project EHRbase
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.



*** Keywords ***

#                       oooo   o8o        .o8                .             
#                       `888   `"'       "888              .o8             
# oooo    ooo  .oooo.    888  oooo   .oooo888   .oooo.   .o888oo  .ooooo.  
#  `88.  .8'  `P  )88b   888  `888  d88' `888  `P  )88b    888   d88' `88b 
#   `88..8'    .oP"888   888   888  888   888   .oP"888    888   888ooo888 
#    `888'    d8(  888   888   888  888   888  d8(  888    888 . 888    .o 
#     `8'     `Y888""8o o888o o888o `Y8bod88P" `Y888""8o   "888" `Y8bod8P' 
#
# [ VALIDATION KEYWORDS ] 

validate response - 201
    Integer    response status    201

    String     response body resourceType    Observation
    String     response body id
    String     response body meta versionId    1


validate response - 404 (with error message)
    # [Arguments]    ${issue_index}    ${error_message}    ${location}
    [Arguments]    ${error_message}
    Integer     response status    404
    String      response body resourceType    OperationOutcome
    String      response body issue 0 diagnostics
    ...         pattern=${error_message}


validate response - 422 (default profile not supported)
    Integer    response status    422

    String     response body resourceType    OperationOutcome
    String     response body issue 0 diagnostics
    ...        pattern=Default profile is not supported for Observation. One of the following profiles is expected:


validate response - 422 (profile not supported)
    Integer    response status    422

    String     response body resourceType    OperationOutcome
    String     response body issue 0 diagnostics
    ...        pattern=Profile http://hl7.org/fhir/StructureDefinition/vitalsigns is not supported for Observation. One of the following profiles is expected:


validate response - 422 (with error message)
    [Arguments]    ${issue_index}    ${error_message}    ${location}
    Integer     response status    422

    String      response body resourceType    OperationOutcome
    String      response body issue ${issue_index} diagnostics
    ...         pattern=${error_message}
    Run Keyword If    $location!=None    String    response body issue ${issue_index} location 0
    ...         ${location}


#                                                 oooo                     
#                                                 `888                     
#  .oooo.o  .ooooo.   .oooo.   oooo d8b  .ooooo.   888 .oo.                
# d88(  "8 d88' `88b `P  )88b  `888""8P d88' `"Y8  888P"Y88b               
# `"Y88b.  888ooo888  .oP"888   888     888        888   888               
# o.  )88b 888    .o d8(  888   888     888   .o8  888   888               
# 8""888P' `Y8bod8P' `Y888""8o d888b    `Y8bod8P' o888o o888o   
#
# [ SEARCH/RETRIEVE ]


get body temperature
    &{resp}             GET    ${BASE_URL}/Observation?identifier=${subject_id}&_profile=http://hl7.org/fhir/StructureDefinition/bodytemp
                        Integer    response status    200
                        String     request method    GET
                        String     response body id
                        String     response body resourceType    Bundle
                        String     response body entry 0 resource resourceType    Observation
                        Output Debug Info To Console


get observation lab
    &{resp}             GET    ${BASE_URL}/Observation?identifier=${subject_id}&_profile=https://www.medizininformatik-initiative.de/fhir/core/modul-labor/StructureDefinition/ObservationLab
                        Integer    response status    200
                        String     request method    GET
                        String     response body id
                        String     response body resourceType    Bundle
                        String     response body entry 0 resource resourceType    Observation
                        Output Debug Info To Console


get coronavirus lab results
    &{resp}             GET    ${BASE_URL}/Observation?identifier=${subject_id}&_profile=https://charite.infectioncontrol.de/fhir/core/StructureDefinition/CoronavirusNachweisTest
                        Integer    response status    200
                        String     request method    GET
                        String     response body id
                        String     response body resourceType    Bundle
                        String     response body entry 0 resource resourceType    Observation
                        Output Debug Info To Console



#                                            .
#                                          .o8
#  .ooooo.  oooo d8b  .ooooo.   .oooo.   .o888oo  .ooooo.
# d88' `"Y8 `888""8P d88' `88b `P  )88b    888   d88' `88b
# 888        888     888ooo888  .oP"888    888   888ooo888
# 888   .o8  888     888    .o d8(  888    888 . 888    .o
# `Y8bod8P' d888b    `Y8bod8P' `Y888""8o   "888" `Y8bod8P'
#
# [ SUCEED CREATING ]


create blood pressure
    # [Arguments]         ${fhir_resource}

    # ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
    #                     # Output    ${payload}
    #                     Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    # &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
    #                     Output Debug Info To Console
    [Arguments]         ${example_json}
    # post fhir resource  Blood Pressure    ${example_json}
    POST /Observation with ehr reference    Blood Pressure    ${example_json}


create body temperature
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


create FIO2
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


create heart rate
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


create observation lab
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


create sofa score
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


create observation
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


create coronavirus lab result
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


create body height
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


create pregnancy status
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


create frailty scale score
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


# [ FAIL CREATING ]
create blood pressure without ehr reference
    [Arguments]         ${example_json}
    # post fhir resource with fake subject reference    Blood Pressure    ${example_json}
    POST /Observation with fake ehr reference    Blood Pressure    ${example_json}


# MAIN HTTP METHOD AND ENDPOINT
POST /Observation
    [Arguments]         ${fhir_resource_name}    ${payload}

    Log To Console      POSTING '${{$fhir_resource_name.upper()}}' OBSERVATION
    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


POST /Observation with ehr reference
    [Arguments]         ${fhir_resource_name}    ${example_json}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${example_json}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}
                        POST /Observation    ${fhir_resource_name}    ${payload}


POST /Observation with fake ehr reference
    [Documentation]     Uses the hardcoded ehr reference in example_json (which does not exist
    ...                 in EHRbase and thus can be considered fake.
    [Arguments]         ${fhir_resource_name}    ${example_json}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${example_json}
                        # Output    ${payload}
                        POST /Observation    ${fhir_resource_name}    ${payload}


# post fhir resource
#     [Arguments]         ${fhir_resource}    ${example_json}

#     Log To Console      POSTING OBSERVATION: ${fhir_resource}
#     ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${example_json}
#                         # Output    ${payload}
#                         Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

#     &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
#                         Output Debug Info To Console


# post fhir resource with fake subject reference
#     [Arguments]         ${fhir_resource}    ${example_json}

#     ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${example_json}
#                         # Output    ${payload}

#     &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
#                         Output Debug Info To Console


create Observation Heart Rate JSON
    [Arguments]         ${resourceType}    ${ID}    ${meta}    ${profile}    ${status}     ${Identifieravailable}    ${Identifiercodingsystem}    ${Identifiercodingcode}    ${Identifiersystem}    ${Identifiervalue}    ${Identifierassigner}    ${Identifierreference}    ${categoryavailable}    ${categorycodingavailable}    ${categorysystem}    ${categorycode}    ${codeavailable}    ${codecodingavailable}    ${code0system}    ${code0code}    ${code0display}    ${code1system}    ${code1code}    ${code1display}    ${codetext}    ${subject}    ${reference}    ${effectivedatetime}    ${vQavailable}    ${vQvalue}    ${vQunit}    ${vQsystem}    ${vQcode}    ${dataabsentreason}    ${responsecode}    ${diagnosticINDEX}    ${diagnosticsENG}    ${diagnosticsDE}

                        prepare new request session  Prefer=return=representation

    &{resp}             Run Keywords
                        ...    ehr.create new ehr               000_ehr_status.json                             AND
                        ...    load JSON                        observation-example-heart-rate-robot.json       AND
                        ...    update Resource Type             ${resourceType}                                 AND
                        ...    update ID                        ${ID}                                           AND
                        ...    update Meta Profile              ${meta}                                         ${profile}                     AND
                        ...    update Status                    ${status}                                       AND
                        ...    update Identifier                ${Identifieravailable}                          ${Identifiercodingsystem}      ${Identifiercodingcode}     ${Identifiersystem}     ${Identifiervalue}      ${Identifierassigner}    ${Identifierreference}    AND
                        ...    update Category                  ${categoryavailable}                            ${categorycodingavailable}     ${categorysystem}           ${categorycode}         AND
                        ...    update Code                      ${codeavailable}                                ${codecodingavailable}         ${code0system}              ${code0code}            ${code0display}         ${code1system}           ${code1code}             ${code1display}    ${codetext}    AND
                        ...    update Subject                   ${subject}                                      ${reference}                   AND
                        ...    update Effective Date Time       ${effectivedatetime}                            AND
                        ...    update Value Quantity            ${vQavailable}                                  ${vQvalue}                     ${vQunit}                   ${vQsystem}            ${vQcode}                AND
                        ...    update Data Absent Reason        ${dataabsentreason}                             AND
                        ...    POST    ${BASE_URL}/Observation    body=${payload}                               AND
                        ...    Output Debug Info To Console                                                     AND
                        ...    validation JSON                  ${responsecode}                                 ${diagnosticINDEX}             ${diagnosticsENG}           ${diagnosticsDE}
