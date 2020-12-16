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
    ...        pattern=The resource does not contain any supported profile. One of the following profiles is expected:

# TODO: remove this KW, use the NEW one below instead
validate response - 422 (with error message)
    [Arguments]    ${issue_index}    ${http_status_code}    ${error_message}    ${location}=${None}
    Integer     response status    ${http_status_code}

    String      response body resourceType    OperationOutcome
    String      response body issue ${issue_index} diagnostics    pattern=${error_message}
    Run Keyword If    $location != None    String    response body issue ${issue_index} location 0
    ...         ${location}

# TODO: remove 'NEW' from KW name
validate response - 422 (with error message NEW)
    [Arguments]     ${http_status_code}    ${error_message}    ${location}=${None}
                    Integer     response status    ${http_status_code}
                    String      response body resourceType    OperationOutcome
    ${issues}=      String      $.issue[*].diagnostics
                    Should Contain Match    ${issues}    regexp=${error_message}

    IF    $location != None
            ${locations}=   String      $.issue[*].location[0]
            Should Contain Match    ${locations}    regexp=${location}
    END



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
    &{resp}             GET    ${BASE_URL}/Observation?subject.identifier=${subject_id}&_profile=http://hl7.org/fhir/StructureDefinition/bodytemp
                        Integer    response status    200
                        String     request method    GET
                        String     response body id
                        String     response body resourceType    Bundle
                        String     response body entry 0 resource resourceType    Observation
                        Output Debug Info To Console


get observation lab
    &{resp}             GET    ${BASE_URL}/Observation?subject.identifier=${subject_id}&_profile=https://www.medizininformatik-initiative.de/fhir/core/modul-labor/StructureDefinition/ObservationLab
                        Integer    response status    200
                        String     request method    GET
                        String     response body id
                        String     response body resourceType    Bundle
                        String     response body entry 0 resource resourceType    Observation
                        Output Debug Info To Console


get coronavirus lab results
    &{resp}             GET    ${BASE_URL}/Observation?subject.identifier=${subject_id}&_profile=https://charite.infectioncontrol.de/fhir/core/StructureDefinition/CoronavirusNachweisTest
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
    [Arguments]         ${example_json}
    POST /Observation with ehr reference    Blood Pressure    ${example_json}


create body temperature
    [Arguments]         ${example_json}
    POST /Observation with ehr reference    Body Temperature    ${example_json}


create FIO2
    [Arguments]         ${example_json}
    POST /Observation with ehr reference    FIO2    ${example_json}


create heart rate
    [Arguments]         ${example_json}
    POST /Observation with ehr reference    Hear Rate    ${example_json}


create observation lab
    [Arguments]         ${example_json}
    POST /Observation with ehr reference    Observation Lab    ${example_json}


create sofa score
    [Arguments]         ${example_json}
    POST /Observation with ehr reference    Sofa Score    ${example_json}


create observation
    [Arguments]         ${example_json}
    POST /Observation with ehr reference    ???    ${example_json}


create coronavirus lab result
    [Arguments]         ${example_json}
    POST /Observation with ehr reference    Coronavirus Lab Result    ${example_json}


create body height
    [Arguments]         ${example_json}
    POST /Observation with ehr reference    Body Height    ${example_json}


create pregnancy status
    [Arguments]         ${example_json}
    POST /Observation with ehr reference    Pregnancy Status    ${example_json}


create frailty scale score
    [Arguments]         ${example_json}
    POST /Observation with ehr reference    Frailty Scale Score    ${example_json}


create smoking status
    [Arguments]         ${example_json}
    POST /Observation with ehr reference    Smoking Status    ${example_json}


create body weight
    [Arguments]         ${example_json}
    POST /Observation with ehr reference    Body Weight    ${example_json}


create patient in icu
    [Arguments]         ${example_json}
    POST /Observation with ehr reference    Patient in Intensive Care Unit (ICU)    ${example_json}


create blood gas panel
    [Arguments]         ${example_json}
    POST /Observation with ehr reference    Blood Gas Panel    ${example_json}


create oxygen saturation
    [Arguments]         ${example_json}
    POST /Observation with ehr reference    Oxygen Saturation in Arterial Blood    ${example_json}


create history of travel
    [Arguments]         ${example_json}
    POST /Observation with ehr reference    History of Travel    ${example_json}


#                                   .                    
#                                 .o8                    
# oo.ooooo.   .ooooo.   .oooo.o .o888oo                  
#  888' `88b d88' `88b d88(  "8   888                    
#  888   888 888   888 `"Y88b.    888                    
#  888   888 888   888 o.  )88b   888 .                  
#  888bod8P' `Y8bod8P' 8""888P'   "888"                  
#  888                                                   
# o888o                                                  
#
# [ VALIDATE POST RESPONSES ]


# MAIN HTTP METHOD AND ENDPOINT
POST /Observation
    [Arguments]         ${fhir_resource_name}    ${payload}

    Log To Console      POSTING '${{ $fhir_resource_name.upper() }}' OBSERVATION
    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


POST /Observation with ehr reference
    [Arguments]         ${fhir_resource_name}    ${example_json}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${example_json}
                        Update Value To Json    ${payload}    $.subject.identifier.value    ${subject_id}
                        Output Debug Info To Console    ${payload}
                        POST /Observation    ${fhir_resource_name}    ${payload}


POST /Observation with fake ehr reference
    [Documentation]     Injects random uuid as ehr reference into example_json. Since it does not exist
    ...                 in EHRbase it can be considered fake reference.
    [Arguments]         ${fhir_resource_name}    ${example_json}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${example_json}
                        Update Value To Json    ${payload}    $.subject.identifier.value    ${{str(uuid.uuid4())}}
                        Output Debug Info To Console    ${payload}
                        POST /Observation    ${fhir_resource_name}    ${payload}


POST /Observation w/o ehr reference
    [Documentation]     Deletes subject property form example_json before posting the payload.
    ...                 This makes the payload invalid since it doesn't have an ehr reference.
    [Arguments]         ${fhir_resource_name}    ${example_json}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${example_json}
                        Delete Object From Json    ${payload}    $.subject
                        Output Debug Info To Console    ${payload}
                        POST /Observation    ${fhir_resource_name}    ${payload}
