# Copyright (c) 2021 Renaud Subiger
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
...						*Author:* Renaud Subiger
Force Tags              consent_create   invalid    create


*** Variables ***
${randstring}                   foobar
${randinteger}                  ${12345}
${randfloat}                    ${12.5}
${randurl}                      http://foobar.com

*** Test Cases ***

001 Create Consent (invalid/missing 'resourceType')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _consent-example-2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``resourceType`` \n\n
    ...                 5. *POST* example JSON to consent endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n                
	[Template]		    create consent with ehr reference
    [Tags]          	resourceType

	# FIELD/PATH					VALUE							HTTP
	# 																CODE
    $.resourceType					missing							422
    $.resourceType					${randstring}					422
    $.resourceType					${EMPTY}						422
    $.resourceType					${randinteger}					422


002 Create Consent (invalid/missing 'identifier')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _consent-example-2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``identifier`` \n\n
    ...                 5. *POST* example JSON to consent endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n                
	[Template]		    create consent w/o ehr reference
    [Tags]              identifier

	# FIELD/PATH			    VALUE					HTTP
    # invalid cases for value
    $.identifier[0].value		    ${EMPTY}				422
    $.identifier[0].value		    ${{ [] }}				422
    $.identifier[0].value		    ${{ {} }}				422
    $.identifier[0].value		    ${123}					422
    
    # invalid cases for system
    $.identifier[0].system		    ${EMPTY}				422
    $.identifier[0].system		    ${{ [] }}				422
    $.identifier[0].system		    ${{ {} }}				422
    $.identifier[0].system		    ${123}					422

    # invalid cases for identifier
    $.identifier			    ${EMPTY}				422
    $.identifier			    ${{ [] }}				422
    $.identifier			    ${{ {} }}				422
    $.identifier			    ${123}					422

003 Create Consent (invalid/missing 'status')
	[Documentation]     1. *CREATE* new an EHR record\n\n
	...                 2. *LOAD* _consent-example-2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``status`` \n\n
    ...                 5. *POST* example JSON to consent endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
	[Template]		    create consent with ehr reference
    [Tags]          	status

	# FIELD/PATH					VALUE							HTTP

	$.status						missing							422
	$.status						${EMPTY}						422
	$.status						${randinteger}					422
	$.status						${randstring}					400

004 Create Consent (invalid/missing 'scope')
	[Documentation]     1. *CREATE* new an EHR record\n\n
	...                 2. *LOAD* _consent-example-2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``scope`` \n\n
    ...                 5. *POST* example JSON to consent endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
	[Template]		    create consent with ehr reference
    [Tags]          	scope

	# FIELD/PATH							VALUE					HTTP																									Location

	#invalid coding
	$.scope.coding    				missing					422
	$.scope.coding    				${EMPTY}				422
	$.scope.coding					${{ [] }}				422
	$.scope.coding					${{ {} }}				422
	$.scope.coding					${{ [{}] }}				422

	#invalid code 0
	$.scope.coding[0].code    		${EMPTY}    	    	422
	$.scope.coding[0].code    		${randstring}	    	422
	$.scope.coding[0].code    		${randinteger}	    	422
    $.scope.coding[0].code 			${{ [] }}				422
	$.scope.coding[0].code 			${{ {} }}				422
	$.scope.coding[0].code 			${{ [{}] }}				422

	# invaild system 0
	$.scope.coding[0].system    		${EMPTY}    	    	422
	$.scope.coding[0].system    		${randstring}	    	422
	$.scope.coding[0].system    		${randinteger}	    	422
	$.scope.coding[0].system    		http://foobar.de      	422
    $.scope.coding[0].system 			${{ [] }}				422
	$.scope.coding[0].system 			${{ {} }}				422
	$.scope.coding[0].system 			${{ [{}] }}				422

005 Create Consent (invalid/missing 'category')
	[Documentation]     1. *CREATE* new an EHR record\n\n
	...                 2. *LOAD* _consent-example-2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``category`` \n\n
    ...                 5. *POST* example JSON to consent endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
	[Template]		    create consent with ehr reference
    [Tags]          	category

	# FIELD/PATH							VALUE					HTTP																									Location

	#invalid coding
	$.category[0].coding    				missing					422
	$.category[0].coding    				${EMPTY}				422
	$.category[0].coding					${{ [] }}				422
	$.category[0].coding					${{ {} }}				422
	$.category[0].coding					${{ [{}] }}				422

	#invalid code 0
	$.category[0].coding[0].code    		${EMPTY}    	    	422
	$.category[0].coding[0].code    		${randstring}	    	422
	$.category[0].coding[0].code    		${randinteger}	    	422
    $.category[0].coding[0].code 			${{ [] }}				422
	$.category[0].coding[0].code 			${{ {} }}				422
	$.category[0].coding[0].code 			${{ [{}] }}				422

	# invaild system 0
	$.category[0].coding[0].system    		${EMPTY}    	    	422
	$.category[0].coding[0].system    		${randstring}	    	422
	$.category[0].coding[0].system    		${randinteger}	    	422
	$.category[0].coding[0].system    		http://foobar.de      	422
    $.category[0].coding[0].system 			${{ [] }}				422
	$.category[0].coding[0].system 			${{ {} }}				422
	$.category[0].coding[0].system 			${{ [{}] }}				422

006 Create Consent (invalid/missing 'provision')
	[Documentation]     1. *CREATE* new an EHR record\n\n
	...                 2. *LOAD* _consent-example-2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``provision`` \n\n
    ...                 5. *POST* example JSON to consent endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
	[Template]		    create consent with ehr reference
    [Tags]          	provision

	# FIELD/PATH							VALUE					HTTP																									Location

	#invalid code
	$.provision.code    				missing					422
	$.provision.code    				${EMPTY}				422
	$.provision.code					${{ [] }}				422
	$.provision.code					${{ {} }}				422
	$.provision.code					${{ [{}] }}				422

	#invalid coding
	$.provision.code[0].coding  		missing					422
	$.provision.code[0].coding   		${EMPTY}				422
	$.provision.code[0].coding 			${{ [] }}				422
	$.provision.code[0].coding 		    ${{ {} }}				422
	$.provision.code[0].coding 			${{ [{}] }}				422

	#invalid code 0
	$.provision.code[0].coding[0].code    		${randinteger}	    	422
    $.provision.code[0].coding[0].code 			${{ [] }}				422
	$.provision.code[0].coding[0].code 			${{ {} }}				422
	$.provision.code[0].coding[0].code 			${{ [{}] }}				422

	# invaild system 0
	$.provision.code[0].coding[0].system    		${randinteger}	    	422
    $.provision.code[0].coding[0].system 			${{ [] }}				422
	$.provision.code[0].coding[0].system 			${{ {} }}				422
	$.provision.code[0].coding[0].system 			${{ [{}] }}				422


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



create consent with ehr reference
	[Arguments]         ${json_path}        ${value}                 ${http_status_code}

						ehr.create new ehr                      000_ehr_status.json
	${payload}=    		generate payload from example json      ${json_path}    ${value}
						consent.POST /Consent  				Consent  ${payload}
						consent.validate response - 422 (w/o error message)  ${http_status_code}



create consent w/o ehr reference
	[Arguments]         ${json_path}        ${value}                ${http_status_code}

	${fake_ehr_ref}=	Evaluate    str(uuid.uuid4())    uuid
						Set Test Variable    ${subject_id}    ${fake_ehr_ref}
	${payload}=    		generate payload from example json      ${json_path}    ${value}
						consent.POST /Consent  				Consent  ${payload}
						consent.validate response - 422 (w/o error message)  ${http_status_code}


generate payload from example json
	[Documentation]		Generates actual request payload using example json as a starting point.
	[Arguments]			${json_path}    ${value}

	${payload}          Load JSON From File    ${DATA_SET_PATH_CONSENT}/consent-example-2.json
                        Update Value To Json    ${payload}    $.patient.identifier.value    ${subject_id}
						Delete Object From Json    ${payload}    $.text

						# comment: delete field/object that has value 'missing' in test case table 
						Run Keyword And Return If   $value=="missing"
						...    	Run Keyword    Delete Object From Json    ${payload}    ${json_path}

						# comment: set value from data table in test case
						Update Value To Json            ${payload}    ${json_path}    ${value}
						Output Debug Info To Console    ${payload}

	[Return]			${payload}