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

#   oooo  .oooooo..o   .oooooo.   ooooo      ooo 
#   `888 d8P'    `Y8  d8P'  `Y8b  `888b.     `8' 
#    888 Y88bo.      888      888  8 `88b.    8  
#    888  `"Y8888o.  888      888  8   `88b.  8  
#    888      `"Y88b 888      888  8     `88b.8  
#    888 oo     .d8P `88b    d88'  8       `888  
#.o. 88P 8""88888P'   `Y8bood8P'  o8o        `8  
#`Y888P                                        
#
# [ UPDATE JSON VALUES ]

load JSON
    [Arguments]         ${fhir_resource}

    ${payload}         Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                       Set Test Variable  ${payload}  ${payload}


validation JSON
    [Arguments]         ${responsecode}    ${diagnosticINDEX}    ${diagnosticsENG}    ${diagnosticsDE}

                        Integer    response status    ${responsecode}

                        Run Keyword And Return If  $responsecode!="201"
                        ...     String     response body issue ${diagnosticINDEX} diagnostics    pattern=${diagnosticsENG}|${diagnosticsDE}


update Resource Type
    [Arguments]         ${resourceType}

                        # Run Keyword only when resourcetype is empty
                        Run Keyword And Return If    $resourceType=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.resourceType    ${resourceType}

                        # Run Keyword only when resourcetype is missing
                        Run Keyword And Return If    $resourceType=="missing"
                        ...    Delete Object From Json  ${payload}  $.resourceType

                        # Else
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.resourceType    ${resourceType}

update ID
    [Arguments]         ${ID}

                        # Run Keyword only when ID is empty
                        Run Keyword And Return If    $ID=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.id   ${ID}

                        # Run Keyword only when ID is missing
                        Run Keyword And Return If    $ID=="missing"
                        ...    Delete Object From Json  ${payload}  $.id

                        # Else
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.id   ${ID}

update Meta Profile
    [Arguments]         ${meta}    ${profile}

                        # Run Keyword only if meta is available
                        Run Keyword And Return If    $meta=="true"
                        ...    update Profile    ${profile}

                        # Run Keyword only if meta is not available
                        Run Keyword And Return If    $meta=="false"
                        ...    Delete Object From Json  ${payload}  $.meta

update Profile
    [Arguments]         ${profile}

                        # Run Keyword only when Profile is empty
                        Run Keyword And Return If    $profile=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.meta.profile[0]    ${profile}

                        # Run Keyword only when Profile is missing
                        Run Keyword And Return If    $profile=="missing"
                        ...    Delete Object From Json  ${payload}  $.meta.profile[0]

                        # Else
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.meta.profile[0]    ${profile}

update Status
    [Arguments]         ${status}

                        # Run Keyword only when Status is empty
                        Run Keyword And Return If    $status=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.status   ${status}

                        # Run Keyword only when Status is missing
                        Run Keyword And Return If    $status=="missing"
                        ...    Delete Object From Json  ${payload}  $.status

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.status   ${status}

update Identifier
    [Arguments]         ${Identifieravailable}    ${Identifiercodingsystem}    ${Identifiercodingcode}    ${Identifiersystem}    ${Identifiervalue}    ${Identifierassigner}    ${Identifierreference}

                        # Run Keywords only if identifier is available
                        Run Keyword And Return If    $Identifieravailable=="true"
                        ...    Run Keywords
                        ...    update Identifier coding system    ${Identifiercodingsystem}     AND
                        ...    update Identifier coding code      ${Identifiercodingcode}       AND
                        ...    update Identifier system           ${Identifiersystem}           AND
                        ...    update Identifier value            ${Identifiervalue}            AND
                        ...    update Identifier assigner         ${Identifierassigner}         ${Identifierreference}

                        # Run Keyword only if Identifier is not available
                        Run Keyword And Return If    $Identifieravailable=="false"
                        ...    Delete Object From Json  ${payload}  $.identifier

update Identifier coding system
    [Arguments]         ${Identifiercodingsystem}

                        # Run Keyword only when Identifier coding system is empty
                        Run Keyword And Return If    $Identifiercodingsystem=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.identifier[0].type.coding[0].system   ${Identifiercodingsystem}

                        # Run Keyword only when Identifier coding system is missing
                        Run Keyword And Return If    $Identifiercodingsystem=="missing"
                        ...    Delete Object From Json    ${payload}    $.identifier[0].type.coding[0].system

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.identifier[0].type.coding[0].system   ${Identifiercodingsystem}

update Identifier coding code
    [Arguments]         ${Identifiercodingcode}

                        # Run Keyword only when Identifier coding code is empty
                        Run Keyword And Return If    $Identifiercodingcode=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.identifier[0].type.coding[0].code   ${Identifiercodingcode}

                        # Run Keyword only when Identifier coding code is missing
                        Run Keyword And Return If    $Identifiercodingcode=="missing"
                        ...    Delete Object From Json    ${payload}    $.identifier[0].type.coding[0].code

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.identifier[0].type.coding[0].code   ${Identifiercodingcode}

update Identifier system
    [Arguments]         ${Identifiersystem}

                        # Run Keyword only when Identifier system is empty
                        Run Keyword And Return If    $Identifiersystem=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.identifier[0].system   ${Identifiersystem}

                        # Run Keyword only when Identifier system is missing
                        Run Keyword And Return If    $Identifiersystem=="missing"
                        ...    Delete Object From Json    ${payload}    $.identifier[0].system
                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.identifier[0].system   ${Identifiersystem}

update Identifier value
    [Arguments]         ${Identifiervalue}

                        # Run Keyword only when Identifier value is empty
                        Run Keyword And Return If    $Identifiervalue=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.identifier[0].value   ${Identifiervalue}

                        # Run Keyword only when Identifier value is missing
                        Run Keyword And Return If    $Identifiervalue=="missing"
                        ...    Delete Object From Json    ${payload}    $.identifier[0].value

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.identifier[0].value   ${Identifiervalue}

update Identifier assigner
    [Arguments]         ${Identifierassigner}    ${Identifierreference}

                        # Run Keywords only if Identifier assigner is available
                        Run Keyword And Return If    $Identifierassigner=="true"
                        ...    update Identifier reference    ${Identifierreference}

                        # Run Keyword only if Identifier assigner is not available
                        Run Keyword And Return If    $Identifierassigner=="false"
                        ...    Delete Object From Json  ${payload}  $.identifier.[0].assigner

update Identifier reference
    [Arguments]         ${Identifierreference}

                        # Run Keyword only when Identifier reference is empty
                        Run Keyword And Return If    $Identifierreference=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.identifier.[0].assigner.reference    ${Identifierreference}

                        # Run Keyword only when Identifier reference is missing
                        Run Keyword And Return If    $Identifierreference=="missing"
                        ...    Delete Object From Json    ${payload}    $.identifier.[0].assigner.reference

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.identifier.[0].assigner.reference    ${Identifierreference}

update Category
    [Arguments]         ${categoryavailable}    ${categorycodingavailable}    ${categorysystem}    ${categorycode}

                        # Run Keyword only if category is available
                        Run Keyword And Return If    $categoryavailable=="true"
                        ...    update Category Coding    ${categorycodingavailable}    ${categorysystem}    ${categorycode}

                        # Run Keyword only if category is not available
                        Run Keyword And Return If    $categoryavailable=="false"
                        ...    Delete Object From Json  ${payload}  $.category

update Category Coding
    [Arguments]         ${categorycodingavailable}    ${categorysystem}    ${categorycode}

                        # Run Keyword only if Category-Coding is available
                        Run Keyword And Return If    $categorycodingavailable=="true"
                        ...    Run Keywords
                        ...    update Category System           ${categorysystem}          AND
                        ...    update Category Code             ${categorycode}

                        # Run Keyword only if Category-Coding is not available
                        Run Keyword And Return If    $categorycodingavailable=="false"
                        ...    Delete Object From Json  ${payload}  $.category[0].coding

update Category System
    [Arguments]         ${categorysystem}

                        # Run Keyword only when Category System is empty
                        Run Keyword And Return If    $categorysystem=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.category[0].coding[0].system    ${categorysystem}

                        # Run Keyword only when Category System is missing
                        Run Keyword And Return If    $categorysystem=="missing"
                        ...    Delete Object From Json    ${payload}    $.category[0].coding[0].system

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.category[0].coding[0].system    ${categorysystem}

update Category Code
    [Arguments]         ${categorycode}

                        # Run Keyword only when Category Code is empty
                        Run Keyword And Return If    $categorycode=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.category[0].coding[0].code    ${categorycode}

                        # Run Keyword only when Category Code is missing
                        Run Keyword And Return If    $categorycode=="missing"
                        ...    Delete Object From Json    ${payload}    $.category[0].coding[0].code

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.category[0].coding[0].code    ${categorycode}

update Code
    [Arguments]         ${codeavailable}    ${codecodingavailable}    ${code0system}    ${code0code}    ${code0display}    ${code1system}    ${code1code}    ${code1display}    ${codetext}

                        # Run Keyword only if code is available
                        Run Keyword And Return If    $codeavailable=="true"
                        ...    update Code Coding    ${codecodingavailable}    ${code0system}    ${code0code}    ${code0display}    ${code1system}    ${code1code}    ${code1display}    ${codetext}

                        # Run Keyword only if code is not available
                        Run Keyword And Return If    $codeavailable=="false"
                        ...    Delete Object From Json  ${payload}  $.code

update Code Coding
    [Arguments]         ${codecodingavailable}    ${code0system}    ${code0code}    ${code0display}    ${code1system}    ${code1code}    ${code1display}    ${codetext}

                        # Run Keyword only if Code-Coding is available
                        Run Keyword And Return If    $codecodingavailable=="true"
                        ...    Run Keywords
                        ...    update Code 0 System             ${code0system}           AND
                        ...    update Code 0 Code               ${code0code}             AND
                        ...    update Code 0 Display            ${code0display}          AND
                        ...    update Code 1 System             ${code1system}           AND
                        ...    update Code 1 Code               ${code1code}             AND
                        ...    update Code 1 Display            ${code1display}          AND
                        ...    update Code Text                 ${codetext}

                        # Run Keyword only if Code-Coding is not available
                        Run Keyword And Return If    $codecodingavailable=="false"
                        ...    Delete Object From Json  ${payload}  $.code.coding

update Code 0 System
    [Arguments]         ${code0system}

                        # Run Keyword only when Code 0 System is empty
                        Run Keyword And Return If    $code0system=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.code.coding[0].system    ${code0system}

                        # Run Keyword only when Code 0 System is missing
                        Run Keyword And Return If    $code0system=="missing"
                        ...    Delete Object From Json    ${payload}    $.code.coding[0].system

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.code.coding[0].system    ${code0system}

update Code 0 Code
    [Arguments]         ${code0code}

                        # Run Keyword only when 0.Code is empty
                        Run Keyword And Return If    $code0code=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.code.coding[0].code    ${code0code}

                        # Run Keyword only when 0.Code is missing
                        Run Keyword And Return If    $code0code=="missing"
                        ...    Delete Object From Json    ${payload}    $.code.coding[0].code

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.code.coding[0].code    ${code0code}

update Code 0 Display
    [Arguments]         ${code0display}

                        # Run Keyword only when 0.Display is empty
                        Run Keyword And Return If    $code0display=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.code.coding[0].display    ${code0display}

                        # Run Keyword only when 0.Display is missing
                        Run Keyword And Return If    $code0display=="missing"
                        ...    Delete Object From Json    ${payload}    $.code.coding[0].display

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.code.coding[0].display    ${code0display}

update Code 1 System
    [Arguments]         ${code1system}

                        # Run Keyword only when Code 1 System is empty
                        Run Keyword And Return If    $code1system=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.code.coding[1].system    ${code1system}

                        # Run Keyword only when Code 1 System is missing
                        Run Keyword And Return If    $code1system=="missing"
                        ...    Delete Object From Json    ${payload}    $.code.coding[1].system

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.code.coding[1].system    ${code1system}

update Code 1 Code
    [Arguments]         ${code1code}

                        # Run Keyword only when 1.Code is empty
                        Run Keyword And Return If    $code1code=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.code.coding[1].code    ${code1code}

                        # Run Keyword only when 1.Code is missing
                        Run Keyword And Return If    $code1code=="missing"
                        ...    Delete Object From Json    ${payload}    $.code.coding[1].code

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.code.coding[1].code    ${code1code}

update Code 1 Display
    [Arguments]         ${code1display}

                        # Run Keyword only when 0.Display is empty
                        Run Keyword And Return If    $code1display=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.code.coding[1].display    ${code1display}

                        # Run Keyword only when 0.Display is missing
                        Run Keyword And Return If    $code1display=="missing"
                        ...    Delete Object From Json    ${payload}    $.code.coding[1].display

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.code.coding[1].display    ${code1display}

update Code Text
    [Arguments]         ${codetext}

                        # Run Keyword only when Code Text is empty
                        Run Keyword And Return If    $codetext=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.code.text    ${codetext}

                        # Run Keyword only when Code Text is missing
                        Run Keyword And Return If    $codetext=="missing"
                        ...    Delete Object From Json    ${payload}    $.code.text

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.code.text    ${codetext}

update Subject
    [Arguments]         ${subject}    ${subjectvalue}

                        # Run Keyword only if subject is available
                        Run Keyword And Return If    $subject=="true"
                        ...    update Subject value    ${subjectvalue}

                        # Run Keyword only if subject is not available
                        Run Keyword And Return If    $subject=="false"
                        ...    Delete Object From Json  ${payload}  $.subject

update Subject value
    [Arguments]         ${subjectvalue}

                        # Run Keyword only when Reference is empty
                        Run Keyword And Return If    $subjectvalue=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.subject.identifier.value    ${subjectvalue}

                        # Run Keyword only when Reference is missing
                        Run Keyword And Return If    $subjectvalue=="missing"
                        ...    Delete Object From Json    ${payload}    $.subject.identifier.value

                        # Run Keyword only when Reference is valid
                        Run Keyword And Return If    $subjectvalue=="valid"
                        ...    Update Value To Json    ${payload}    $.subject.identifier.value    ${subject_id}

                        # Run Keyword only when Reference is invalid UUID
                        Run Keyword And Return If    $subjectvalue=="invalid"
                        ...    Update Value To Json    ${payload}    $.subject.identifier.value    ${{str(uuid.uuid4())}}

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.subject.identifier.value    ${subjectvalue}

update Effective Date Time
    [Arguments]         ${effectivedatetime}

                        # Run Keyword only when Effective Date Time is empty
                        Run Keyword And Return If    $effectivedatetime=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.effectiveDateTime   ${effectivedatetime}

                        # Run Keyword only when Effective Date Time is missing
                        Run Keyword And Return If    $effectivedatetime=="missing"
                        ...    Delete Object From Json  ${payload}  $.effectiveDateTime

                        # Else
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.effectiveDateTime   ${effectivedatetime}

update Value Quantity
    [Arguments]         ${vQavailable}    ${vQvalue}    ${vQunit}    ${vQsystem}    ${vQcode}

                        # Run Keyword only if value quantity is available
                        Run Keyword And Return If    $vQavailable=="true"
                        ...    Run Keywords
                        ...    update Value Quantity Value           ${vQvalue}          AND
                        ...    update Value Quantity Unit            ${vQunit}           AND
                        ...    update Value Quantity System          ${vQsystem}         AND
                        ...    update Value Quantity Code            ${vQcode}

                        # Run Keyword only if value quantity is not available
                        Run Keyword And Return If    $vQavailable=="false"
                        ...    Delete Object From Json  ${payload}  $.valueQuantity

update Value Quantity Value
    [Arguments]         ${vQvalue}

                        # Run Keyword only when value Quantity Value is empty
                        Run Keyword And Return If    $vQvalue=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.valueQuantity.value   ${vQvalue}

                        # Run Keyword only when value Quantity Value is missing
                        Run Keyword And Return If    $vQvalue=="missing"
                        ...    Delete Object From Json  ${payload}  $.valueQuantity.value

                        # Else
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.valueQuantity.value   ${vQvalue}

update Value Quantity Unit
    [Arguments]         ${vQunit}

                        # Run Keyword only when value Quantity Unit is empty
                        Run Keyword And Return If    $vQunit=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.valueQuantity.unit   ${vQunit}

                        # Run Keyword only when value Quantity Unit is missing
                        Run Keyword And Return If    $vQunit=="missing"
                        ...    Delete Object From Json  ${payload}  $.valueQuantity.unit

                        # Else
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.valueQuantity.unit   ${vQunit}

update Value Quantity System
    [Arguments]         ${vQsystem}

                        # Run Keyword only when value Quantity System is empty
                        Run Keyword And Return If    $vQsystem=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.valueQuantity.system    ${vQsystem}

                        # Run Keyword only when value Quantity System is missing
                        Run Keyword And Return If    $vQsystem=="missing"
                        ...    Delete Object From Json  ${payload}  $.valueQuantity.system

                        # Else
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.valueQuantity.system    ${vQsystem}

update Value Quantity Code
    [Arguments]         ${vQcode}

                        # Run Keyword only when value Quantity Code is empty
                        Run Keyword And Return If    $vQcode=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.valueQuantity.code   ${vQcode}

                        # Run Keyword only when value Quantity Code is missing
                        Run Keyword And Return If    $vQcode=="missing"
                        ...    Delete Object From Json  ${payload}  $.valueQuantity.code

                        # Else
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.valueQuantity.code   ${vQcode}

update Data Absent Reason
    [Arguments]         ${dataabsentreason}

                        # Run Keyword only if Data Absent Reason is available and valid (todo)
                        Run Keyword And Return If    $dataabsentreason=="truevalid"
                        ...    Run Keywords
                        ...    Update Value To Json    ${payload}    $.dataAbsentReason.coding[0].system          http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation           AND
                        ...    Update Value To Json    ${payload}    $.dataAbsentReason.coding[0].code            unknown                                                                      AND
                        ...    Update Value To Json    ${payload}    $.dataAbsentReason.coding[0].display         unknown  

                        # Run Keyword only if Data Absent Reason is available and invalid (todo)
                        Run Keyword And Return If    $dataabsentreason=="trueinvalid"
                        ...    Run Keywords
                        ...    Update Value To Json    ${payload}    $.dataAbsentReason.coding[0].system          ${EMPTY}           AND
                        ...    Update Value To Json    ${payload}    $.dataAbsentReason.coding[0].code            ${EMPTY}           AND
                        ...    Update Value To Json    ${payload}    $.dataAbsentReason.coding[0].display         ${EMPTY}

                        # Run Keyword only if Data Absent Reason is not available
                        Run Keyword And Return If    $dataabsentreason=="false"
                        ...    Delete Object From Json  ${payload}  $.dataAbsentReason

