# Copyright (c) 2021 Peter Wohlfarth (Appsfactory GmbH)
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

# oooo    oooo oooooooooooo oooooo   oooo oooooo   oooooo     oooo   .oooooo.   ooooooooo.   oooooooooo.    .oooooo..o
# `888   .8P'  `888'     `8  `888.   .8'   `888.    `888.     .8'   d8P'  `Y8b  `888   `Y88. `888'   `Y8b  d8P'    `Y8
#  888  d8'     888           `888. .8'     `888.   .8888.   .8'   888      888  888   .d88'  888      888 Y88bo.
#  88888[       888oooo8       `888.8'       `888  .8'`888. .8'    888      888  888ooo88P'   888      888  `"Y8888o.
#  888`88b.     888    "        `888'         `888.8'  `888.8'     888      888  888`88b.     888      888      `"Y88b
#  888  `88b.   888       o      888           `888'    `888'      `88b    d88'  888  `88b.   888     d88' oo     .d8P
# o888o  o888o o888ooooood8     o888o           `8'      `8'        `Y8bood8P'  o888o  o888o o888bood8P'   8""88888P'
#
# [ HIGH LEVEL KEYWORDS ]


create body-height with ehr reference
	[Arguments]         ${json_path}        ${value}                 ${http_status_code}
	...					${error_message}    ${location}=${None}

						ehr.create new ehr                      000_ehr_status.json
	${payload}=    		generate payload from example json      ${json_path}                ${value}
						observation.POST /Observation           body-height            		${payload}
						observation.validate response - 422 (with error message NEW)        ${http_status_code}
						...															        ${error_message}
						...															        ${location}


create body-height w/o ehr reference    
	[Arguments]         ${json_path}        ${value}                ${http_status_code}
	...					${error_message}    ${location}=${None}

	${fake_ehr_ref}=	Evaluate    str(uuid.uuid4())    uuid
						Set Test Variable    ${subject_id}    ${fake_ehr_ref}
	${payload}=    		generate payload from example json    ${json_path}    ${value}
						observation.POST /Observation    body-height    ${payload}
						observation.validate response - 422 (with error message NEW)      ${http_status_code}
						...															      ${error_message}
						...															      ${location}


generate payload from example json
	[Documentation]		Generates actual request payload using example json as a starting point.
	[Arguments]			${json_path}    ${value}

	${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/create-body-height.json
                        Update Value To Json    ${payload}    $.subject.identifier.value    ${subject_id}
						Delete Object From Json    ${payload}    $.text

						# comment: delete field/object that has value 'missing' in test case table 
						Run Keyword And Return If   $value=="missing"
						...    	Run Keyword    Delete Object From Json    ${payload}    ${json_path}

						# comment: set value from data table in test case
						Update Value To Json            ${payload}    ${json_path}    ${value}
						Output Debug Info To Console    ${payload}

	[Return]			${payload}


create body-height JSON
    [Arguments]         ${resourceType}    				${ID}    						${meta}    							${profile}
	...					${Identifieravailable}          ${Identifiersystem}         	${Identifiervalue}                  ${status}
	...                 ${categoryavailable}    	    ${categorycodingavailable}    	${categorysystem}					${categorycode}
	...					${codeavailable}    			${codecodingavailable}    		${code0system}						${code0code}
	...					${code0display}					${subject}    					${subjectvalue}						${effectivedatetime}
	...					${vCCavailabe}					${vCCCodingavailable}			${vCC0System}						${vCC0Code}
	...					${vCC0Display}					${http_status_code}    			${error_message}					${location}

                        prepare new request session    Prefer=return=representation

    &{resp}             Run Keywords
                        ...    ehr.create new ehr               000_ehr_status.json                             AND
                        ...    load JSON                        create-body-height.json				    AND
                        ...    update Resource Type             ${resourceType}                                 AND
                        ...    update ID                        ${ID}                                           AND
                        ...    update Meta Profile              ${meta}                                         ${profile}                    	AND
						...    update Identifier with system and value                                          ${Identifieravailable}          ${Identifiersystem}         ${Identifiervalue}      AND
                        ...    update Status                    ${status}                                       AND
                        ...    update Category                  ${categoryavailable}                            ${categorycodingavailable}     	${categorysystem}           ${categorycode}         AND
                        ...    update Code 0                    ${codeavailable}                                ${codecodingavailable}         	${code0system}              ${code0code}            ${code0display}     AND
                        ...    update Subject                   ${subject}                                      ${subjectvalue}                	AND
                        ...    update Effective Date Time       ${effectivedatetime}                            AND
						...    update vCC						${vCCavailabe}									${vCCCodingavailable}			${vCC0System}				${vCC0Code}				${vCC0Display}		AND
                        ...    POST    ${BASE_URL}/Observation    body=${payload}                               AND
                        ...    Output Debug Info To Console                                                     AND
                        ...    validate response - 422 (with error message NEW)									${http_status_code}    			${error_message}    		${location}


generate payload from example json with data absentreason
	[Documentation]		Generates actual request payload using example json as a starting point.
	[Arguments]			${json_path}    ${value}

	${dict_dataabsentreason}			Create Dictionary	dataAbsentReason=${{ {"coding": [{"system": "http://terminology.hl7.org/CodeSystem/data-absent-reason", "code": "unknown", "display": "unknown"}], "text": "body-height"} }}

	${payload}          Load JSON From File    		${DATA_SET_PATH_OBSERVATION}/create-body-height.json
                        Update Value To Json    	${payload}    $.subject.identifier.value    			${subject_id}
						Delete Object From Json    	${payload}    $.text
						Delete Object From Json    	${payload}    $.valueCodeableConcept
						Add Object To Json  		${payload}    $												${dict_dataabsentreason}

						# comment: delete field/object that has value 'missing' in test case table 
						Run Keyword And Return If   $value=="missing"
						...    	Run Keyword    Delete Object From Json    ${payload}    ${json_path}

						# comment: set value from data table in test case
						Update Value To Json            ${payload}    ${json_path}    ${value}
						Output Debug Info To Console    ${payload}

	[Return]			${payload}


create body-height with ehr reference AND data absentreason
	[Arguments]         ${json_path}        ${value}                 ${http_status_code}
	...					${error_message}    ${location}=${None}

						ehr.create new ehr    000_ehr_status.json
	${payload}=    		generate payload from example json with data absentreason    ${json_path}    ${value}
						observation.POST /Observation    body-height    ${payload}
						observation.validate response - 422 (with error message NEW)     ${http_status_code}
						...															     ${error_message}
						...															     ${location}


create with DataAbsentReason
    [Arguments]         ${fhir_resource_name}    ${example_json}

	${dict_dataabsentreason}			Create Dictionary	dataAbsentReason=${{ {"coding": [{"system": "http://terminology.hl7.org/CodeSystem/data-absent-reason", "code": "unknown", "display": "unknown"}], "text": "body-height"} }}

    ${payload}          Load JSON From File    		${DATA_SET_PATH_OBSERVATION}/${example_json}
                        Update Value To Json    	${payload}    $.subject.identifier.value    				${subject_id}
						Add Object To Json  		${payload}    $												${dict_dataabsentreason}
                        Output Debug Info To Console    ${payload}
                        POST /Observation    		${fhir_resource_name}    ${payload}