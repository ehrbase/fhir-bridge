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
Resource    ../_resources/keywords/generic.robot

Test Setup              generic.prepare new request session    Prefer=return=representation
...															   Authorization=${AUTHORIZATION['Authorization']}
Documentation           *NOTE:* Use Regular Expressions to replace braces () as described here:
...                	    https://json-schema.org/understanding-json-schema/reference/regular_expressions.html#example \n\n
...						*Author:* Peter Wohlfarth
Force Tags              observation_create    history-of-travel    invalid    create


*** Variables ***
${randstring}                   foobar
${randinteger}                  ${12345}


*** Test Cases ***
001 Create History of Travel (invalid/missing 'resourceType')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-history-of-travel.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``resourceType`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n                
	[Template]		    create History of Travel with ehr reference
    [Tags]          	resourceType

	# FIELD/PATH					VALUE							HTTP
	# 																CODE
    $.resourceType					missing							422
    $.resourceType					${randstring}					422
    $.resourceType					${EMPTY}						422
    $.resourceType					${randinteger}					422



002 Create History of Travel (invalid/missing 'identifier')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-history-of-travel.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID: ${subject_id}_ which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``identifier`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 
	[Template]			create History of Travel with ehr reference
    [Tags]              identifier

	# FIELD/PATH	                				VALUE							HTTP
    # invalid identifier															CODE
    $.identifier									${EMPTY}						422
    $.identifier									${{ [] }}						422
    $.identifier									${{ {} }}						422
    $.identifier									${{ [{}] }}						422

    # invalid type
    $.identifier[0].type							${EMPTY}						422
    $.identifier[0].type							${{ [] }}						422
    $.identifier[0].type							${{ {} }}						422
    $.identifier[0].type							${{ [{}] }}						422

    # invalid type coding
    $.identifier[0].type.coding						${EMPTY}						422
    $.identifier[0].type.coding						${{ [] }}						422
    $.identifier[0].type.coding						${{ {} }}						422
    $.identifier[0].type.coding						${{ [{}] }}						422

    # invalid type coding system
    $.identifier[0].type.coding[0].system			${EMPTY}					 	422
    $.identifier[0].type.coding[0].system			${randinteger}				 	422
    $.identifier[0].type.coding[0].system			${randstring}				 	422

    # invalid type coding code
    $.identifier[0].type.coding[0].code				${EMPTY}					 	422
    $.identifier[0].type.coding[0].code				${randinteger}				 	422

    # invalid system
    $.identifier[0].system							${EMPTY}					 	422
    $.identifier[0].system							${randinteger}				 	422
    $.identifier[0].system							${randstring}				 	422

    # invalid value
    $.identifier[0].value							${EMPTY}					 	422
    $.identifier[0].value							${randinteger}				 	422

    # invalid assigner
    $.identifier[0].assigner						${EMPTY}						422
    $.identifier[0].assigner						${{ [] }}						422
    $.identifier[0].assigner						${{ {} }}						422
    $.identifier[0].assigner						${{ [{}] }}						422

    # invalid assigner reference
    $.identifier[0].assigner.reference				${EMPTY}					 	422
    $.identifier[0].assigner.reference				${randinteger}				 	422



003 Create History of Travel (invalid/missing 'category')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-history-of-travel.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``Category`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n               
	[Template]			create History of Travel with ehr reference
    [Tags]              category

	# FIELD/PATH							VALUE					HTTP
	# 																CODE

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



004 Create History of Travel (invalid/missing 'code')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-history-of-travel.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``Code`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n                 
	[Template]			create History of Travel with ehr reference
    [Tags]              code

	# FIELD/PATH							VALUE					HTTP																									Location
	# 																CODE

	# invalid code
	$.code									missing					422
	$.code									${EMPTY}				422
	$.code									${{ [] }}				422
	$.code									${{ {} }}				422
	$.code									${{ [{}] }}				422

	# invalid coding 0
	$.code.coding[0]   	 					missing					422
	$.code.coding[0]	    				${EMPTY}				422
	$.code.coding[0]						${{ [] }}				422
	$.code.coding[0]						${{ {} }}				422
	$.code.coding[0]						${{ [{}] }}				422

	# invalid Code Coding 0 System
	$.code.coding[0].system					missing					422
	$.code.coding[0].system					${EMPTY}				422
	$.code.coding[0].system					http://foobar.de		422
	$.code.coding[0].system					${randstring}			422
	$.code.coding[0].system					${randinteger}			422
    $.code.coding[0].system      			${{ [] }}				422
	$.code.coding[0].system      			${{ {} }}				422
	$.code.coding[0].system      			${{ [{}] }}				422

	# invalid Code Coding 0 Code
	$.code.coding[0].code					missing					422
	$.code.coding[0].code					${EMPTY}				422
	$.code.coding[0].code					${randstring}			422
	$.code.coding[0].code					${randinteger}			422
    $.code.coding[0].code      			    ${{ [] }}				422
	$.code.coding[0].code      			    ${{ {} }}				422
	$.code.coding[0].code      			    ${{ [{}] }}				422

	# invalid coding 1
	# $.code.coding[1]   	 					missing					422
	# parameter code.coding is 1..1 in https://simplifier.net/ForschungsnetzCovid-19/HistoryOfTravel/ 
	# If one valid coding array is present, the example stays valid.
	$.code.coding[1]	    				${EMPTY}				422
	$.code.coding[1]						${{ [] }}				422
	$.code.coding[1]						${{ {} }}				422
	$.code.coding[1]						${{ [{}] }}				422

	# invalid Code Coding 1 System
	# $.code.coding[1].system					missing					422
	# parameter code.coding is 1..1 in https://simplifier.net/ForschungsnetzCovid-19/HistoryOfTravel/ 
	# If one valid coding array is present, the example stays valid.	
	$.code.coding[1].system					${EMPTY}				422
	# $.code.coding[1].system					http://foobar.de		422
	# parameter code.coding is 1..1 in https://simplifier.net/ForschungsnetzCovid-19/HistoryOfTravel/ 
	# If one valid coding array is present, the example stays valid. FHIR-bridge takes the valid parameter from code.coding[0].system	
	$.code.coding[1].system					${randstring}			422
	$.code.coding[1].system					${randinteger}			422
    $.code.coding[1].system      			${{ [] }}				422
	$.code.coding[1].system      			${{ {} }}				422
	$.code.coding[1].system      			${{ [{}] }}				422

	# invalid Code Coding 1 Code
	# $.code.coding[1].code					missing					422
	# parameter code.coding is 1..1 in https://simplifier.net/ForschungsnetzCovid-19/HistoryOfTravel/ 
	# If one valid coding array is present, the example stays valid.
	$.code.coding[1].code					${EMPTY}				422
	# $.code.coding[1].code					${randstring}			422
	# parameter code.coding is 1..1 in https://simplifier.net/ForschungsnetzCovid-19/HistoryOfTravel/ 
	# If one valid coding array is present, the example stays valid. FHIR-bridge takes the valid parameter from code.coding[0].code
	$.code.coding[1].code					${randinteger}			422
    $.code.coding[1].code      			    ${{ [] }}				422
	$.code.coding[1].code      			    ${{ {} }}				422
	$.code.coding[1].code      			    ${{ [{}] }}				422



005 Create History of Travel (invalid/missing 'subject')
	[Documentation]     1. *LOAD* _create-history-of-travel.json_ \n\n
	...                 2. *UPDATE* values for attribute ``Subject`` \n\n
    ...                 3. *POST* example JSON to observation endpoint \n\n
	...                 4. *VALIDATE* the response status              
	[Template]		    create History of Travel w/o ehr reference 
    [Tags]          	subject

	# FIELD/PATH					VALUE							HTTP
	# 																CODE
    # invalid cases for value
    $.subject.identifier.value		missing							422
    # Deprecated: $.subject.identifier.value		foobar							422
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
    # Deprecated: $.subject.identifier.value      ${{str(uuid.uuid4())}}    		422



006 Create History of Travel (invalid/missing 'valueCodeableConcept')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-history-of-travel.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``valueCodeableConcept`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
	[Template]			create History of Travel with ehr reference
    [Tags]              valueCodeableConcept

	# FIELD/PATH								VALUE					HTTP
	# 																	CODE
	# missing valueCodeableConcept
	$.valueCodeableConcept						${EMPTY}				422
	
	# wrong format
	$.valueCodeableConcept						${{ [] }}				422
	$.valueCodeableConcept						${{ {} }}				422
	$.valueCodeableConcept						${{ [{}] }}				422
	
	# missing coding
	$.valueCodeableConcept.coding 				missing					422
	$.valueCodeableConcept.coding				${EMPTY}				422
	$.valueCodeableConcept.coding				${{ [] }}				422
	$.valueCodeableConcept.coding				${{ {} }}				422
	$.valueCodeableConcept.coding				${{ [{}] }}				422
	
	# invalid system
	$.valueCodeableConcept.coding[0].system		missing					422
	$.valueCodeableConcept.coding[0].system		${EMPTY}				422
	$.valueCodeableConcept.coding[0].system		${randstring}			422
	$.valueCodeableConcept.coding[0].system		${randinteger}			422
	$.valueCodeableConcept.coding[0].system		${{ [] }}				422
	$.valueCodeableConcept.coding[0].system		${{ {} }}				422
	$.valueCodeableConcept.coding[0].system		${{ [{}] }}				422
	
	# invalid code
	$.valueCodeableConcept.coding[0].code		missing					422
	$.valueCodeableConcept.coding[0].code		${EMPTY}				422
	$.valueCodeableConcept.coding[0].code		${randinteger}			422
	$.valueCodeableConcept.coding[0].code		${{ [] }}				422
	$.valueCodeableConcept.coding[0].code		${{ {} }}				422
	$.valueCodeableConcept.coding[0].code		${{ [{}] }}				422
	
	# invalid display
	$.valueCodeableConcept.coding[0].display	${EMPTY}				422
	$.valueCodeableConcept.coding[0].display	${randinteger}			422
	$.valueCodeableConcept.coding[0].display	${{ [] }}				422
	$.valueCodeableConcept.coding[0].display	${{ {} }}				422
	$.valueCodeableConcept.coding[0].display	${{ [{}] }}				422



007 Create History of Travel (invalid/missing 'component')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-history-of-travel.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``component`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
	[Template]			create History of Travel with ehr reference
    [Tags]              component

	# FIELD/PATH			VALUE					HTTP
	# 												CODE
	# $.component				missing					422
	# See Bug Trace 04
	$.component				${EMPTY}				422
	$.component				${{ [] }}				422
	$.component				${{ {} }}				422
	$.component				${{ [{}] }}				422



008 Create History of Travel (invalid/missing 'component[0] start date')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-history-of-travel.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``component[0]`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
	[Template]			create History of Travel with ehr reference
    [Tags]              component-travel-start-date

	# FIELD/PATH							VALUE					HTTP
	# 																CODE
	$.component[0]							${EMPTY}				422
	$.component[0]							${{ [] }}				422
	$.component[0]							${{ {} }}				422
	$.component[0]							${{ [{}] }}				422

	# invalid code
	$.component[0].code						missing					422
	$.component[0].code						${EMPTY}				422
	$.component[0].code						${{ [] }}				422
	$.component[0].code						${{ {} }}				422
	$.component[0].code						${{ [{}] }}				422

	# invalid coding 0
	$.component[0].code.coding[0]   	 	missing					422
	$.component[0].code.coding[0]	    	${EMPTY}				422
	$.component[0].code.coding[0]			${{ [] }}				422
	$.component[0].code.coding[0]			${{ {} }}				422
	$.component[0].code.coding[0]			${{ [{}] }}				422

	# invalid Code Coding 0 System
	$.component[0].code.coding[0].system	missing					422
	$.component[0].code.coding[0].system	${EMPTY}				422
	$.component[0].code.coding[0].system	${randstring}			422
	$.component[0].code.coding[0].system	${randinteger}			422
	$.component[0].code.coding[0].system    ${{ [] }}				422
	$.component[0].code.coding[0].system    ${{ {} }}				422
	$.component[0].code.coding[0].system    ${{ [{}] }}				422

	# invalid Code Coding 0 Code
	# $.component[0].code.coding[0].code		missing					422
	# See Bug Trace 01
	$.component[0].code.coding[0].code		${EMPTY}				422
	$.component[0].code.coding[0].code		${randinteger}			422
	$.component[0].code.coding[0].code      ${{ [] }}				422
	$.component[0].code.coding[0].code      ${{ {} }}				422
	$.component[0].code.coding[0].code      ${{ [{}] }}				422

	# invalid valueDateTime
	# $.component[0].valueDateTime			missing					422
	# See Bug Trace 03
	$.component[0].valueDateTime			${EMPTY}				422
	$.component[0].valueDateTime			${{ [] }}				422
	$.component[0].valueDateTime			${{ {} }}				422
	$.component[0].valueDateTime			${{ [{}] }}				422
	$.component[0].valueDateTime			2020-09-00				422
	$.component[0].valueDateTime			2020-09-32				422
	$.component[0].valueDateTime			2020-09-dd				422
	$.component[0].valueDateTime			2020-00-21				422
	$.component[0].valueDateTime			2020-13-21				422
	$.component[0].valueDateTime			2020-mm-21				422
	$.component[0].valueDateTime			0000-09-21				422
	$.component[0].valueDateTime			10000-09-21				422
	$.component[0].valueDateTime			yyyy-09-21				422
	$.component[0].valueDateTime			21.09.2020				422
	$.component[0].valueDateTime			${randstring}			422
	$.component[0].valueDateTime			${randinteger}			422



009 Create History of Travel (invalid/missing 'component[1] country')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-history-of-travel.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``component[1]`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
	[Template]			create History of Travel with ehr reference
    [Tags]              component-country

	# FIELD/PATH												VALUE					HTTP
	# 															CODE
	$.component[1]												${EMPTY}				422
	$.component[1]												${{ [] }}				422
	$.component[1]												${{ {} }}				422
	$.component[1]												${{ [{}] }}				422

	# invalid code
	$.component[1].code											missing					422
	$.component[1].code											${EMPTY}				422
	$.component[1].code											${{ [] }}				422
	$.component[1].code											${{ {} }}				422
	$.component[1].code											${{ [{}] }}				422

	# invalid coding 1
	$.component[1].code.coding[0]   	 						missing					422
	$.component[1].code.coding[0]	    						${EMPTY}				422
	$.component[1].code.coding[0]								${{ [] }}				422
	$.component[1].code.coding[0]								${{ {} }}				422
	$.component[1].code.coding[0]								${{ [{}] }}				422

	# invalid Code Coding 1 System
	$.component[1].code.coding[0].system						missing					422
	$.component[1].code.coding[0].system						${EMPTY}				422
	$.component[1].code.coding[0].system						${randstring}			422
	$.component[1].code.coding[0].system						${randinteger}			422
	$.component[1].code.coding[0].system    					${{ [] }}				422
	$.component[1].code.coding[0].system    					${{ {} }}				422
	$.component[1].code.coding[0].system    					${{ [{}] }}				422

	# invalid Code Coding 1 Code
	# $.component[1].code.coding[0].code							missing					422
	# See Bug Trace 01
	$.component[1].code.coding[0].code							${EMPTY}				422
	$.component[1].code.coding[0].code							${randinteger}			422
	$.component[1].code.coding[0].code      					${{ [] }}				422
	$.component[1].code.coding[0].code      					${{ {} }}				422
	$.component[1].code.coding[0].code      					${{ [{}] }}				422

	# empty
	$.component[1].valueCodeableConcept							${EMPTY}				422
	
	# wrong format
	$.component[1].valueCodeableConcept							${{ [] }}				422
	$.component[1].valueCodeableConcept							${{ {} }}				422
	$.component[1].valueCodeableConcept							${{ [{}] }}				422

	# missing coding
	# $.component[1].valueCodeableConcept.coding 					missing					422
	# See Bug Trace 02
	$.component[1].valueCodeableConcept.coding					${EMPTY}				422
	$.component[1].valueCodeableConcept.coding					${{ [] }}				422
	$.component[1].valueCodeableConcept.coding					${{ {} }}				422
	$.component[1].valueCodeableConcept.coding					${{ [{}] }}				422

	# invalid system
	# $.component[1].valueCodeableConcept.coding[0].system		missing					422
	# See Bug Trace 05
	$.component[1].valueCodeableConcept.coding[0].system		${EMPTY}				422
	# $.component[1].valueCodeableConcept.coding[0].system		http://foobar.de		422
	# See Bug Trace 05
	$.component[1].valueCodeableConcept.coding[0].system		${randstring}			422
	$.component[1].valueCodeableConcept.coding[0].system		${randinteger}			422
	$.component[1].valueCodeableConcept.coding[0].system		${{ [] }}				422
	$.component[1].valueCodeableConcept.coding[0].system		${{ {} }}				422
	$.component[1].valueCodeableConcept.coding[0].system		${{ [{}] }}				422

	# invalid code
	# $.component[1].valueCodeableConcept.coding[0].code			missing					422
	# # See Bug Trace 05
	$.component[1].valueCodeableConcept.coding[0].code			${EMPTY}				422
	# $.component[1].valueCodeableConcept.coding[0].code			${randstring}			422
	# # See Bug Trace 05
	$.component[1].valueCodeableConcept.coding[0].code			${randinteger}			422
	$.component[1].valueCodeableConcept.coding[0].code			${{ [] }}				422
	$.component[1].valueCodeableConcept.coding[0].code			${{ {} }}				422
	$.component[1].valueCodeableConcept.coding[0].code			${{ [{}] }}				422



010 Create History of Travel (invalid/missing 'component[2] state')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-history-of-travel.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``component[2]`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
	[Template]			create History of Travel with ehr reference
    [Tags]              component-state
	
	# FIELD/PATH												VALUE					HTTP
	# 															CODE
	$.component[2]												${EMPTY}				422
	$.component[2]												${{ [] }}				422
	$.component[2]												${{ {} }}				422
	$.component[2]												${{ [{}] }}				422

	# invalid code
	$.component[2].code											missing					422
	$.component[2].code											${EMPTY}				422
	$.component[2].code											${{ [] }}				422
	$.component[2].code											${{ {} }}				422
	$.component[2].code											${{ [{}] }}				422

	# invalid coding 2
	$.component[2].code.coding[0]   	 						missing					422
	$.component[2].code.coding[0]	    						${EMPTY}				422
	$.component[2].code.coding[0]								${{ [] }}				422
	$.component[2].code.coding[0]								${{ {} }}				422
	$.component[2].code.coding[0]								${{ [{}] }}				422

	# invalid Code Coding 2 System
	$.component[2].code.coding[0].system						missing					422
	$.component[2].code.coding[0].system						${EMPTY}				422
	$.component[2].code.coding[0].system						${randstring}			422
	$.component[2].code.coding[0].system						${randinteger}			422
	$.component[2].code.coding[0].system    					${{ [] }}				422
	$.component[2].code.coding[0].system    					${{ {} }}				422
	$.component[2].code.coding[0].system    					${{ [{}] }}				422

	# invalid Code Coding 2 Code
	# $.component[2].code.coding[0].code							missing					422
	# See Bug Trace 01
	$.component[2].code.coding[0].code							${EMPTY}				422
	$.component[2].code.coding[0].code							${randinteger}			422
	$.component[2].code.coding[0].code      					${{ [] }}				422
	$.component[2].code.coding[0].code      					${{ {} }}				422
	$.component[2].code.coding[0].code      					${{ [{}] }}				422

	# empty
	$.component[2].valueCodeableConcept							${EMPTY}				422
	
	# wrong format
	$.component[2].valueCodeableConcept							${{ [] }}				422
	$.component[2].valueCodeableConcept							${{ {} }}				422
	$.component[2].valueCodeableConcept							${{ [{}] }}				422

	# missing coding
	# $.component[2].valueCodeableConcept.coding 					missing					422
	# See Bug Trace 02
	$.component[2].valueCodeableConcept.coding					${EMPTY}				422
	$.component[2].valueCodeableConcept.coding					${{ [] }}				422
	$.component[2].valueCodeableConcept.coding					${{ {} }}				422
	$.component[2].valueCodeableConcept.coding					${{ [{}] }}				422

	# invalid system
	# $.component[2].valueCodeableConcept.coding[0].system		missing					422
	# See Bug Trace 05
	$.component[2].valueCodeableConcept.coding[0].system		${EMPTY}				422
	# $.component[2].valueCodeableConcept.coding[0].system		http://foobar.de		422
	# See Bug Trace 05
	$.component[2].valueCodeableConcept.coding[0].system		${randstring}			422
	$.component[2].valueCodeableConcept.coding[0].system		${randinteger}			422
	$.component[2].valueCodeableConcept.coding[0].system		${{ [] }}				422
	$.component[2].valueCodeableConcept.coding[0].system		${{ {} }}				422
	$.component[2].valueCodeableConcept.coding[0].system		${{ [{}] }}				422

	# invalid code
	# $.component[2].valueCodeableConcept.coding[0].code			missing					422
	# See Bug Trace 05
	$.component[2].valueCodeableConcept.coding[0].code			${EMPTY}				422
	# $.component[2].valueCodeableConcept.coding[0].code			${randstring}			422
	# See Bug Trace 05
	$.component[2].valueCodeableConcept.coding[0].code			${randinteger}			422
	$.component[2].valueCodeableConcept.coding[0].code			${{ [] }}				422
	$.component[2].valueCodeableConcept.coding[0].code			${{ {} }}				422
	$.component[2].valueCodeableConcept.coding[0].code			${{ [{}] }}				422



011 Create History of Travel (invalid/missing 'component[3] city')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-history-of-travel.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``component[3]`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
	[Template]			create History of Travel with ehr reference
    [Tags]              component-start-city
	
	# FIELD/PATH												VALUE					HTTP
	# 															CODE
	$.component[3]												${EMPTY}				422
	$.component[3]												${{ [] }}				422
	$.component[3]												${{ {} }}				422
	$.component[3]												${{ [{}] }}				422

	# invalid code
	$.component[3].code											missing					422
	$.component[3].code											${EMPTY}				422
	$.component[3].code											${{ [] }}				422
	$.component[3].code											${{ {} }}				422
	$.component[3].code											${{ [{}] }}				422

	# invalid coding 2
	$.component[3].code.coding[0]   	 						missing					422
	$.component[3].code.coding[0]	    						${EMPTY}				422
	$.component[3].code.coding[0]								${{ [] }}				422
	$.component[3].code.coding[0]								${{ {} }}				422
	$.component[3].code.coding[0]								${{ [{}] }}				422

	# invalid Code Coding 2 System
	$.component[3].code.coding[0].system						missing					422
	$.component[3].code.coding[0].system						${EMPTY}				422
	$.component[3].code.coding[0].system						${randstring}			422
	$.component[3].code.coding[0].system						${randinteger}			422
	$.component[3].code.coding[0].system    					${{ [] }}				422
	$.component[3].code.coding[0].system    					${{ {} }}				422
	$.component[3].code.coding[0].system    					${{ [{}] }}				422

	# invalid Code Coding 2 Code
	# $.component[3].code.coding[0].code							missing					422
	# See Bug Trace 01
	$.component[3].code.coding[0].code							${EMPTY}				422
	$.component[3].code.coding[0].code							${randinteger}			422
	$.component[3].code.coding[0].code      					${{ [] }}				422
	$.component[3].code.coding[0].code      					${{ {} }}				422
	$.component[3].code.coding[0].code      					${{ [{}] }}				422



012 Create History of Travel (invalid/missing 'component[4] end date')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-history-of-travel.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``component[4]`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
	[Template]			create History of Travel with ehr reference
    [Tags]              component-travel-end-date
	
	# FIELD/PATH									VALUE					HTTP
	# 																		CODE
	$.component[4]									${EMPTY}				422
	$.component[4]									${{ [] }}				422
	$.component[4]									${{ {} }}				422
	$.component[4]									${{ [{}] }}				422

	# invalid code
	$.component[4].code								missing					422
	$.component[4].code								${EMPTY}				422
	$.component[4].code								${{ [] }}				422
	$.component[4].code								${{ {} }}				422
	$.component[4].code								${{ [{}] }}				422

	# invalid coding 2
	$.component[4].code.coding[0]   	 			missing					422
	$.component[4].code.coding[0]	    			${EMPTY}				422
	$.component[4].code.coding[0]					${{ [] }}				422
	$.component[4].code.coding[0]					${{ {} }}				422
	$.component[4].code.coding[0]					${{ [{}] }}				422

	# invalid Code Coding 2 System
	$.component[4].code.coding[0].system			missing					422
	$.component[4].code.coding[0].system			${EMPTY}				422
	$.component[4].code.coding[0].system			${randstring}			422
	$.component[4].code.coding[0].system			${randinteger}			422
	$.component[4].code.coding[0].system    		${{ [] }}				422
	$.component[4].code.coding[0].system    		${{ {} }}				422
	$.component[4].code.coding[0].system    		${{ [{}] }}				422

	# invalid Code Coding 2 Code
	# $.component[4].code.coding[0].code				missing					422
	# See Bug Trace 01
	$.component[4].code.coding[0].code				${EMPTY}				422
	$.component[4].code.coding[0].code				${randinteger}			422
	$.component[4].code.coding[0].code      		${{ [] }}				422
	$.component[4].code.coding[0].code      		${{ {} }}				422
	$.component[4].code.coding[0].code      		${{ [{}] }}				422
	
	# invalid valueDateTime
	# $.component[4].valueDateTime					missing					422
	# See Bug Trace 03
	$.component[4].valueDateTime					${EMPTY}				422
	$.component[4].valueDateTime					${{ [] }}				422
	$.component[4].valueDateTime					${{ {} }}				422
	$.component[4].valueDateTime					${{ [{}] }}				422
	$.component[4].valueDateTime					2020-09-00				422
	$.component[4].valueDateTime					2020-09-32				422
	$.component[4].valueDateTime					2020-09-dd				422
	$.component[4].valueDateTime					2020-00-21				422
	$.component[4].valueDateTime					2020-13-21				422
	$.component[4].valueDateTime					2020-mm-21				422
	$.component[4].valueDateTime					0000-09-21				422
	$.component[4].valueDateTime					10000-09-21				422
	$.component[4].valueDateTime					yyyy-09-21				422
	$.component[4].valueDateTime					21.09.2020				422
	$.component[4].valueDateTime					${randstring}			422
	$.component[4].valueDateTime					${randinteger}			422

#--------------------------------------------------------------------------------------------------------------------------------------------------------------------
# BUG TRACE
#--------------------------------------------------------------------------------------------------------------------------------------------------------------------

01 Bug Trace component[x].code.coding[0].code = missing
	[Documentation]		Bug Trace tests for component[x].code.coding[0].code = missing
	[Template]			create History of Travel with ehr reference
	[Tags]    			not-ready    not-ready_bug    component    code

	# FIELD/PATH									VALUE					HTTP
	# 																		CODE
	$.component[0].code.coding[0].code				missing					422
	$.component[1].code.coding[0].code				missing					422
	$.component[2].code.coding[0].code				missing					422
	$.component[3].code.coding[0].code				missing					422
	$.component[4].code.coding[0].code				missing					422


02 Bug Trace component[x].valueCodeableConcept.coding = missing
	[Documentation]		Bug Trace tests for component[x].valueCodeableConcept.coding = missing
	[Template]			create History of Travel with ehr reference
	[Tags]    			not-ready    not-ready_bug    valueCodeableConcept    500
	
	# FIELD/PATH									VALUE					HTTP
	# 																		CODE
	$.component[1].valueCodeableConcept.coding		missing					422
	$.component[2].valueCodeableConcept.coding		missing					422


03 Bug Trace component[x].valueDateTime = missing
	[Documentation]		Bug Trace tests for component[x].valueDatetime = missing
	[Template]			create History of Travel with ehr reference
	[Tags]    			not-ready    not-ready_bug   valueDateTime
	
	# FIELD/PATH									VALUE					HTTP
	# 																		CODE
	$.component[0].valueDateTime					missing					422
	$.component[4].valueDateTime					missing					422


04 Bug Trace component = missing
	[Documentation]		Bug Trace tests for component = missing
	[Template]			create History of Travel with ehr reference
	[Tags]    			not-ready    not-ready_bug   component

	# FIELD/PATH									VALUE					HTTP
	# 																		CODE
	$.component										missing					422


05 Bug Trace component[x].valueCodeableConcept unexpected 201
	[Documentation]		Bug Trace tests for component[x].valueCodeableConcept unexpected 201 validations
	[Template]			create History of Travel with ehr reference
	[Tags]    			not-ready    not-ready_bug   valueCodeableConcept    201

	# FIELD/PATH											VALUE					HTTP
	# 																				CODE
	$.component[1].valueCodeableConcept.coding[0].system 	missing					422
	$.component[1].valueCodeableConcept.coding[0].system 	http://foobar.de		422
	$.component[1].valueCodeableConcept.coding[0].code 		missing					422
	$.component[1].valueCodeableConcept.coding[0].code 		${randstring}			422
	$.component[2].valueCodeableConcept.coding[0].system 	missing					422
	$.component[2].valueCodeableConcept.coding[0].system 	http://foobar.de		422
	$.component[2].valueCodeableConcept.coding[0].code 		missing					422
	$.component[2].valueCodeableConcept.coding[0].code 		${randstring}			422


06 Bug Trace component[x].code.coding[0].system unexpected 201
	[Documentation]		Bug Trace tests for component[x].code.coding[0].system unexpected 201 validations
	[Template]			create History of Travel with ehr reference
	[Tags]    			not-ready    not-ready_bug   valueCodeableConcept    201

	# FIELD/PATH											VALUE					HTTP
	# 																				CODE
	$.component[0].code.coding[0].system					missing					422
	$.component[1].code.coding[0].system					missing					422
	$.component[2].code.coding[0].system					missing					422
	$.component[3].code.coding[0].system					missing					422
	$.component[4].code.coding[0].system					missing					422
	
	[Teardown]	TRACE GITHUB ISSUE    568    bug
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



create History of Travel with ehr reference
	[Arguments]         ${json_path}        ${value}                 ${http_status_code}

						ehr.create new ehr                      000_ehr_status.json
	${payload}=    		generate payload from example json      ${json_path}                ${value}
						observation.POST /Observation           History of Travel            	${payload}
						observation.validate response - 422 (w/o error message)  ${http_status_code}


create History of Travel w/o ehr reference    
	[Arguments]         ${json_path}        ${value}                ${http_status_code}

	${fake_ehr_ref}=	Evaluate    str(uuid.uuid4())    uuid
						Set Test Variable    ${subject_id}    ${fake_ehr_ref}
	${payload}=    		generate payload from example json      ${json_path}                ${value}
						observation.POST /Observation           History of Travel            	${payload}
						observation.validate response - 422 (w/o error message)  ${http_status_code}


generate payload from example json
	[Documentation]		Generates actual request payload using example json as a starting point.
	[Arguments]			${json_path}    ${value}

	${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/create-history-of-travel.json
                        Update Value To Json    ${payload}    $.subject.identifier.value    ${subject_id}
						Delete Object From Json    ${payload}    $.text

						# comment: delete field/object that has value 'missing' in test case table 
						Run Keyword And Return If   $value=="missing"
						...    	Run Keyword    Delete Object From Json    ${payload}    ${json_path}

						# comment: set value from data table in test case
						Update Value To Json            ${payload}    ${json_path}    ${value}
						Output Debug Info To Console    ${payload}

	[Return]			${payload}