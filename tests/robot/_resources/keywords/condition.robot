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
    [Documentation]     Validates response of POST to /Condition endpoint
                        Integer    response status    201


validate response - 422 (Unprocessable Entity)
                        Integer    response status    422
                        String     $.issue[0]['diagnostics']
                        ...        Specified profile type was 'Observation', but found type 'Condition'


#                                                 oooo                     
#                                                 `888                     
#  .oooo.o  .ooooo.   .oooo.   oooo d8b  .ooooo.   888 .oo.                
# d88(  "8 d88' `88b `P  )88b  `888""8P d88' `"Y8  888P"Y88b               
# `"Y88b.  888ooo888  .oP"888   888     888        888   888               
# o.  )88b 888    .o d8(  888   888     888   .o8  888   888               
# 8""888P' `Y8bod8P' `Y888""8o d888b    `Y8bod8P' o888o o888o   
#
# [ SEARCH/RETRIEVE ]


get diagnose condition
    &{resp}             GET    ${BASE_URL}/Condition?subject.identifier=${subject_id}
                        Integer    response status    200
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


create diagnose condition
    [Arguments]         ${example_json}
    POST /Condition with ehr reference    Diagnose Condition    ${example_json}


create symptoms-covid-19
    [Arguments]         ${example_json}
    POST /Condition with ehr reference    Symptoms Covid-19    ${example_json}


create diabetes mellitus
    [Arguments]         ${text}    ${example_json}
    POST /Condition with ehr reference    ${text}    ${example_json}


create rheumatological immunological diseases
    [Arguments]         ${text}    ${example_json}
    POST /Condition with ehr reference    ${text}    ${example_json}


create chronic lung diseases
    [Arguments]         ${text}    ${example_json}
    POST /Condition with ehr reference    ${text}    ${example_json}


create chronic liver diseases
    [Arguments]         ${text}    ${example_json}
    POST /Condition with ehr reference    ${text}    ${example_json}


create malignant neoplastic disease
    [Arguments]         ${example_json}
    POST /Condition with ehr reference    Malignant Neoplastic Disease    ${example_json}


create diagnosis covid-19
    [Arguments]         ${example_json}
    POST /Condition with ehr reference    Diagnosis Covid-19    ${example_json}


create chronic kidney diseases
    [Arguments]         ${text}    ${example_json}
    POST /Condition with ehr reference    ${text}    ${example_json}


create chronic neurological or mental diseases
    [Arguments]         ${text}    ${example_json}
    POST /Condition with ehr reference    ${text}    ${example_json}


create cardiovascular diseases
    [Arguments]         ${text}    ${example_json}
    POST /Condition with ehr reference    ${text}    ${example_json}


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


POST /Condition
    [Arguments]         ${fhir_resource_name}    ${payload}

    Log To Console      POSTING '${{ $fhir_resource_name.upper() }}' CONDITION
    &{resp}             POST    ${BASE_URL}/Condition    body=${payload}
                        Output Debug Info To Console


POST /Condition with ehr reference
    [Arguments]         ${fhir_resource_name}    ${example_json}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_CONDITION}/${example_json}
                        Update Value To Json    ${payload}    $.subject.identifier.value    ${subject_id}
                        Output Debug Info To Console    ${payload}
                        POST /Condition    ${fhir_resource_name}    ${payload}
