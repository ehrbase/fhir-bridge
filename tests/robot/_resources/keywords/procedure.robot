# Copyright (c) 2019 Wladislaw Wagner (Vitasystems GmbH), P. Wohlfarth (Appsfactory),
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

    String     response body resourceType    Procedure
    String     response body id
    String     response body meta versionId    1


validate response - 422 (w/o error message)
    [Arguments]     ${http_status_code}
                    Integer     response status    ${http_status_code}
                    String      response body resourceType    OperationOutcome


#                                            .
#                                          .o8
#  .ooooo.  oooo d8b  .ooooo.   .oooo.   .o888oo  .ooooo.
# d88' `"Y8 `888""8P d88' `88b `P  )88b    888   d88' `88b
# 888        888     888ooo888  .oP"888    888   888ooo888
# 888   .o8  888     888    .o d8(  888    888 . 888    .o
# `Y8bod8P' d888b    `Y8bod8P' `Y888""8o   "888" `Y8bod8P'
#
# [ SUCEED CREATING ]


create procedure
    [Arguments]         ${example_json}
    POST /Procedure with ehr reference    Procedure    ${example_json}


create radiology procedures
    [Arguments]         ${text}    ${example_json}
    POST /Procedure with ehr reference    ${text}    ${example_json}


create dialysis
    [Arguments]         ${text}    ${example_json}
    POST /Procedure with ehr reference    ${text}    ${example_json}


create dialysis with status change
    [Arguments]         ${text}    ${status}    ${example_json}
    POST /Procedure with ehr reference with status change    ${text}    ${status}    ${example_json}


create prone position
    [Arguments]         ${text}    ${example_json}
    POST /Procedure with ehr reference    ${text}    ${example_json}


create prone position with status change
    [Arguments]         ${text}    ${status}    ${example_json}
    POST /Procedure with ehr reference with status change    ${text}    ${status}    ${example_json}


create extracorporeal membrane oxygenation
    [Arguments]         ${text}    ${example_json}
    POST /Procedure with ehr reference    ${text}    ${example_json}


create extracorporeal membrane oxygenation with status change
    [Arguments]         ${text}    ${status}    ${example_json}
    POST /Procedure with ehr reference with status change    ${text}    ${status}    ${example_json}


create respiratory therapies
    [Arguments]         ${text}    ${example_json}
    POST /Procedure with ehr reference    ${text}    ${example_json}


create respiratory therapies with status change
    [Arguments]         ${text}    ${status}    ${example_json}
    POST /Procedure with ehr reference with status change    ${text}    ${status}    ${example_json}


create apheresis
    [Arguments]         ${text}    ${example_json}
    POST /Procedure with ehr reference    ${text}    ${example_json}


create apheresis with status change
    [Arguments]         ${text}    ${status}    ${example_json}
    POST /Procedure with ehr reference with status change    ${text}    ${status}    ${example_json}


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


POST /Procedure
    [Arguments]         ${fhir_resource_name}    ${payload}

    Log To Console      POSTING '${{ $fhir_resource_name.upper() }}' PROCEDURE
    &{resp}             POST    ${BASE_URL}/Procedure    body=${payload}
                        Output Debug Info To Console


POST /Procedure with ehr reference
    [Arguments]         ${fhir_resource_name}    ${example_json}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_PROCEDURE}/${example_json}
                        Update Value To Json    ${payload}    $.subject.identifier.value    ${subject_id}
                        Output Debug Info To Console    ${payload}
                        POST /Procedure    ${fhir_resource_name}    ${payload}


POST /Procedure with ehr reference with status change
    [Arguments]         ${fhir_resource_name}    ${status}    ${example_json}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_PROCEDURE}/${example_json}
                        Update Value To Json    ${payload}    $.subject.identifier.value    ${subject_id}
                        Update Value To Json    ${payload}    $.status                      ${status}
                        Output Debug Info To Console    ${payload}
                        POST /Procedure    ${fhir_resource_name}    ${payload}