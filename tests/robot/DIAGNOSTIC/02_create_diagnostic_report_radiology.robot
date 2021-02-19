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


*** Settings ***
Resource                ${EXECDIR}/robot/_resources/suite_settings.robot

Test Setup              generic.prepare new request session    Prefer=return=representation
...															   Authorization=${AUTHORIZATION['Authorization']}
Documentation           *NOTE:* Use Regular Expressions to replace braces () as described here:
...                	    https://json-schema.org/understanding-json-schema/reference/regular_expressions.html#example \n\n
...						*Author:* Peter Wohlfarth
Force Tags              diagnostic_report_create    diagnostic_report    diagnostic_report_radiology    invalid    create


*** Variables ***
${randstring}                   foobar
${randinteger}                  ${12345}


*** Test Cases ***

001 Resource type
002 category
003 code 
004 conclusionCode
005 conclusion



006 Create Diagnostic Report Radiology (Invalid/Missing 'subject')
    [Documentation]     1. *LOAD* _create-respiratory-rate.json_ \n\n
	...                 2. *UPDATE* values for attribute ``Subject`` \n\n
    ...                 3. *POST* example JSON to observation endpoint \n\n
	...                 4. *VALIDATE* the response status              
	[Template]		    create Respiratory Rate w/o ehr reference 
    [Tags]          	subject

	# FIELD/PATH					VALUE							HTTP
	# 																CODE
    # invalid cases for value
    $.subject.identifier.value		missing							422
    $.subject.identifier.value		foobar							422
    $.subject.identifier.value		${EMPTY}						422
    $.subject.identifier.value		${{ [] }}						422
    $.subject.identifier.value		${{ {} }}						422
    $.subject.identifier.value		${123}							422

	# invalid cases for system
    $.subject.identifier.system		foobar							422
    $.subject.identifier.system		${EMPTY}						422
    $.subject.identifier.system		${{ [] }}						422
    $.subject.identifier.system		${{ {} }}						422
    $.subject.identifier.system		${123}							422

	# invalid cases for identifier
    $.subject.identifier			missing							422
    $.subject.identifier			${EMPTY}						422
    $.subject.identifier			${{ [] }}						422
    $.subject.identifier			${{ {} }}						422
    $.subject.identifier			${123}							422

	# invalid cases for subject
    $.subject						missing							422
    $.subject						${EMPTY}						422
    $.subject						${{ [] }}						422
    $.subject						${{ {} }}						422
    $.subject						${123}							422
	
	# comment: random uuid												
    $.subject.identifier.value      ${{str(uuid.uuid4())}}    		422
#--------------------------------------------------------------------------------------------------------------------------------------------------------------------

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



create Diagnostic Report Radiology with ehr reference
	[Arguments]         ${json_path}        ${value}                 ${http_status_code}

						ehr.create new ehr                      000_ehr_status.json
	${payload}=    		generate payload from example json    ${json_path}    ${value}
						diagnostic.POST /Diagnostic  Diagnostic Report Radiology    ${payload}
						observation.validate response - 422 (w/o error message)    ${http_status_code}


create Diagnostic Report Radiology w/o ehr reference    
	[Arguments]         ${json_path}        ${value}                ${http_status_code}

	${fake_ehr_ref}=	Evaluate    str(uuid.uuid4())    uuid
						Set Test Variable    ${subject_id}    ${fake_ehr_ref}
	${payload}=    		generate payload from example json    ${json_path}    ${value}
						diagnostic.POST /Diagnostic  Diagnostic Report Radiology    ${payload}
						observation.validate response - 422 (w/o error message)    ${http_status_code}


generate payload from example json
	[Documentation]		Generates actual request payload using example json as a starting point.
	[Arguments]			${json_path}    ${value}

	${payload}          Load JSON From File    ${DATA_SET_PATH_DIAGNOSTIC}/RadiologyReport/create-radiology-report-normal-finding.json
                        Update Value To Json    ${payload}    $.subject.identifier.value    ${subject_id}
						Delete Object From Json    ${payload}    $.text

						# comment: delete field/object that has value 'missing' in test case table 
						Run Keyword And Return If   $value=="missing"
						...    	Run Keyword    Delete Object From Json    ${payload}    ${json_path}

						# comment: set value from data table in test case
						Update Value To Json            ${payload}    ${json_path}    ${value}
						Output Debug Info To Console    ${payload}

	[Return]			${payload}