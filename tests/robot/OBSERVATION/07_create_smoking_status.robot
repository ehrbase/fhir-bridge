# Copyright (c) 2020 Dave Petzold (Appsfactory GmbH)
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
...															   Authorization=Basic bXl1c2VyOm15UGFzc3dvcmQ0MzI=
Documentation           *NOTE:* use Regular Expressions to replace braces () as described here:
...                	    https://json-schema.org/understanding-json-schema/reference/regular_expressions.html#example
Force Tags              create    smoking-status    invalid



*** Variables ***
${smoking_status-url}			https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/smoking-status
${randstring}                   foobar
${randinteger}                  ${12345}




*** Test Cases ***

001 Create Smoking Status (Invalid/Missing 'Subject')
    [Documentation]     1. *LOAD* _create-smoking-status.json_ \n\n
	...                 2. *UPDATE* values for attribute ``Subject`` \n\n
    ...                 3. *POST* example JSON to observation endpoint \n\n
	...                 4. *VALIDATE* the response status \n\n
    ...                 5. *VALIDATE* outcome against diagnostic text & location

	[Template]			create smoking status w/o ehr reference

	[Tags]              subject

	# FIELD/PATH					VALUE							HTTP	ERROR MESSAGE																	Location
	# 																CODE

	# invalid cases for value
    $.subject.identifier.value		missing							422		 Subject identifier is required
    $.subject.identifier.value		foobar							422		 EhrId not found for subject 'foobar'
    $.subject.identifier.value		${EMPTY}						422		 @value cannot be empty                                        					Observation.subject.identifier.value
    $.subject.identifier.value		${{ [] }}						422		 This property must be an simple value, not an array           					Observation.subject.identifier.value
    $.subject.identifier.value		${{ {} }}						422		 This property must be an simple value, not an object          					Observation.subject.identifier.value
    $.subject.identifier.value		${123}							422		 Error parsing JSON: the primitive value must be a string      					Observation.subject.identifier.value

	# invalid cases for system
    $.subject.identifier.system		foobar							422		Identifier.system must be an absolute reference, not a local reference
    $.subject.identifier.system		${EMPTY}						422		@value cannot be empty                                         					Observation.subject.identifier.system
    $.subject.identifier.system		${{ [] }}						422		 This property must be an simple value, not an array           					Observation.subject.identifier.system
    $.subject.identifier.system		${{ {} }}						422		 This property must be an simple value, not an object          					Observation.subject.identifier.system
    $.subject.identifier.system		${123}							422		 Error parsing JSON: the primitive value must be a string      					Observation.subject.identifier.system

	# invalid cases for identifier
    $.subject.identifier			missing							422		 Object must have some content                                 					Observation.subject
    $.subject.identifier			${EMPTY}						422		 This property must be an Object, not a primitive property     					Observation.subject.identifier
    $.subject.identifier			${{ [] }}						422		 This property must be an Object, not an array                 					Observation.subject.identifier
    $.subject.identifier			${{ {} }}						422		 Object must have some content                                 					Observation.subject.identifier
    $.subject.identifier			${123}							422		 This property must be an Object, not a primitive property     					Observation.subject.identifier

	# invalid cases for subject
    $.subject						missing							422		 Observation.subject: minimum required = 1, but only found 0 .from ${smoking_status-url}
    $.subject						${EMPTY}						422		 This property must be an Object, not a primitive property     					Observation.subject
    $.subject						${{ [] }}						422		 This property must be an Object, not an array                 					Observation.subject
    $.subject						${{ {} }}						422		 Object must have some content                                 					Observation.subject
    $.subject						${123}							422		 This property must be an Object, not a primitive property     					Observation.subject
	
	# comment: random uuid																			 regex for uuid
    $.subject.identifier.value    ${{str(uuid.uuid4())}}    		422     EhrId not found for subject
	

002 Create Smoking Status (Invalid/Missing 'resourceType')
	[Documentation]     1. *Create* new an EHR record\n\n 
	...                 2. *LOAD* _create-smoking-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``resourceType`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]		    create smoking status with ehr reference
    [Tags]          	resourceType

	# FIELD/PATH					VALUE							HTTP	ERROR MESSAGE																	Location
	# 																CODE
    $.resourceType					missing							422		Unable to find resourceType propert
    $.resourceType					${randstring}					422		This does not appear to be a FHIR resource .unknown name '${randstring}'.
    $.resourceType					${EMPTY}						422		This does not appear to be a FHIR resource .unknown name ''.
    $.resourceType					${randinteger}					422		This does not appear to be a FHIR resource .unknown name '${randinteger}'.


003 Create Smoking Status (Invalid/Missing 'ID')
	[Documentation]     1. *Create* new an EHR record\n\n 
	...                 2. *LOAD* _create-smoking-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``ID`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]		    create smoking status with ehr reference
    [Tags]          	ID

	# FIELD/PATH					VALUE							HTTP	ERROR MESSAGE																	Location
	# 																CODE
	$.id							${EMPTY}						422		@value cannot be empty															Observation.id												
	$.id							${randinteger}					422		Error parsing JSON: the primitive value must be a string						Observation.id


004 Create Smoking Status (Invalid/Missing 'meta')
	[Documentation]     1. *Create* new an EHR record\n\n 
	...                 2. *LOAD* _create-smoking-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID: ${subject_id}_ which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``meta`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create smoking status with ehr reference
    [Tags]              meta

	# FIELD/PATH					VALUE							HTTP	ERROR MESSAGE																									Location
	# 																CODE
	$.meta							missing							422    	Default profile is not supported for Observation. One of the following profiles is expected: .https://.*
	$.meta.profile					missing							422    	Object must have some content																					Observation.meta
	$.meta.profile[0]				${randinteger}					422    	Canonical URLs must be absolute URLs if they are not fragment references .${randinteger}.						Observation.meta.profile.0.
	$.meta.profile[0]				${randstring}					422    	Canonical URLs must be absolute URLs if they are not fragment references .${randstring}.						Observation.meta.profile.0.
	$.meta.profile    				${{ ["invalid_url"] }}		  	422    	Canonical URLs must be absolute URLs if they are not fragment references .invalid_url.							Observation.meta.profile.0.
	$.meta.profile    				${{ ["http://wrong.url"] }}	   	422    	Profile reference 'http://wrong.url' could not be resolved, so has not been checked								Observation.meta.profile.0.
	$.meta.profile					${EMPTY}						422    	This property must be an Array, not a a primitive property														Observation.meta.profile
	
	# comment: the next one sets the value to an empty list/array []
	$.meta.profile					${{ [] }}						422    	Default profile is not supported for Observation. One of the following profiles is expected: .https://.*
	
	# comment: the next one sets value to an empty object {}
	$.meta.profile					${{ {} }}						422    	This property must be an Array, not a an object


005 Create Smoking Status (Invalid/Missing 'Status')
	[Documentation]     1. *Create* new an EHR record\n\n 
	...                 2. *LOAD* _create-smoking-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``Status`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]		    create smoking status with ehr reference
    [Tags]          	status

	# FIELD/PATH					VALUE							HTTP	ERROR MESSAGE																								Location
	# 																CODE
	$.status						missing							422		Observation.status: minimum required = 1, but only found 0 .from https://.*									Observation
	$.status						${EMPTY}						422		@value cannot be empty																						Observation.status
	$.status						${randinteger}					422		Error parsing JSON: the primitive value must be a string													Observation.status
	$.status						${randstring}					400		Failed to parse request body as JSON resource. Error was: .element=\"status\". Invalid attribute value \"foobar\": Unknown ObservationStatus code '${randstring}'


006 Create Smoking Status (Invalid/Missing 'category')
	[Documentation]     1. *Create* new an EHR record\n\n 
	...                 2. *LOAD* _create-smoking-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``Category`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create smoking status with ehr reference
    [Tags]              category

	# FIELD/PATH							VALUE					HTTP	ERROR MESSAGE																								Location
	# 																CODE

	# invalid category
#	$.category								missing					422    	Observation.category: minimum required = 1, but only found 0 .from ${smoking_status-url}
	$.category								${{ [] }}				422    	Array cannot be empty - the property should not be present if it has no values								Observation.category
	$.category								${{ {} }}				422    	This property must be an Array, not an Object																Observation.category
	$.category								${{ [{}] }}				422    	Object must have some content																				Observation.category

	#invalid coding
	$.category[0].coding    				missing					422    	Object must have some content																				Observation.category.0.
	$.category[0].coding    				${EMPTY}				422    	This property must be an Array, not a primitive property													Observation.category.0..coding
	
	#invalid code 0
#	$.category[0].coding[0].code    		missing    		    	422    	This element does not match any known slice defined in the profile ${smoking_status-url}
	$.category[0].coding[0].code    		${EMPTY}    	    	422    	@value cannot be empty																						Observation.category.0..coding.0..code
#	$.category[0].coding[0].code    		${randstring}	    	422    	This element does not match any known slice defined in the profile ${smoking_status-url}					Observation.category.0.
	$.category[0].coding[0].code    		${randinteger}	    	422    	Error parsing JSON: the primitive value must be a string													Observation.category.0..coding.0..code
	
	# invaild system 0
#	$.category[0].coding[0].system    		missing    		    	422    	A code with no system has no defined meaning. A system should be provided									Observation.category.0..coding.0.
	$.category[0].coding[0].system    		${EMPTY}    	    	422    	@value cannot be empty																						Observation.category.0..coding.0..system
	$.category[0].coding[0].system    		${randstring}	    	422    	Coding.system must be an absolute reference, not a local reference											Observation.category.0..coding.0.
	$.category[0].coding[0].system    		${randinteger}	    	422    	Error parsing JSON: the primitive value must be a string													Observation.category.0..coding.0..system
#	$.category[0].coding[0].system    		http://foobar.de      	422    	This element does not match any known slice defined in the profile ${smoking_status-url}					Observation.category[0]


007 Create Smoking Status (Invalid/Missing 'code')
	[Documentation]     1. *Create* new an EHR record\n\n 
	...                 2. *LOAD* _create-smoking-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``Code`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create smoking status with ehr reference
    [Tags]              code

	# FIELD/PATH							VALUE					HTTP	ERROR MESSAGE																								Location
	# 																CODE

	# invalid code
	$.code									missing					422    	Observation.code: minimum required = 1, but only found 0 .from ${smoking_status-url}						Observation
	$.code									${{ [] }}				422    	Observation.code: minimum required = 1, but only found 0 .from ${smoking_status-url}						Observation
	$.code									${{ {} }}				422    	Object must have some content																				Observation.code
	$.code									${{ [{}] }}				422    	This property must be an Object, not an array																Observation.code

	# invalid coding
	$.code.coding   	 					missing					422    	Object must have some content																				Observation.code
	$.code.coding	    					${EMPTY}				422    	This property must be an Array, not a primitive property													Observation.code.coding

	# invalid Code Coding 0 System
    $.code.coding[0].system					missing					422    	A code with no system has no defined meaning. A system should be provided									Observation.code.coding.0.
	$.code.coding[0].system					${EMPTY}				422    	@value cannot be empty																						Observation.code.coding.0..system
	$.code.coding[0].system					http://foobar.de		422    	The pattern .system http://loinc.org, code 72166-2, and display 'null'. defined in the profile https://*	Observation.code
	$.code.coding[0].system					${randstring}			422    	Coding.system must be an absolute reference, not a local reference											Observation.code.coding.0.
	$.code.coding[0].system					${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.code.coding.0..system

	# invalid Code Coding 0 Code
    $.code.coding[0].code					missing					422    	The pattern .system http://loinc.org, code 72166-2, and display 'null'. defined in the profile https://*	Observation.code
	$.code.coding[0].code					${EMPTY}				422    	@value cannot be empty																						Observation.code.coding.0..code
	$.code.coding[0].code					${randstring}			422    	The pattern .system http://loinc.org, code 72166-2, and display 'null'. defined in the profile https://*	Observation.code
	$.code.coding[0].code					${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.code.coding.0..code

	# invalid Code Coding 0 Display
    $.code.coding[0].display				${EMPTY}				422    	@value cannot be empty																						Observation.code.coding.0..display
	$.code.coding[0].display				${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.code.coding.0..display


008 Create Smoking Status (Invalid/Missing 'effectiveDateTime')
	[Documentation]     1. *Create* new an EHR record\n\n 
	...                 2. *LOAD* _create-smoking-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``effectiveDateTime`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create smoking status with ehr reference
    [Tags]              effectiveDateTime

	# FIELD/PATH							VALUE					HTTP	ERROR MESSAGE																								Location
	# 																CODE
	
	# missing attribute
	$.effectiveDateTime						missing					422    	Observation.effective.x.: minimum required = 1, but only found 0 .from https:/*								Observation
	$.effectiveDateTime						${EMPTY}				422    	@value cannot be empty																						Observation.effective.ofType.dateTime.
	
	# wrong format
	$.effectiveDateTime						${{ [] }}				422    	This property must be an simple value, not an array															Observation.effective.x.
	$.effectiveDateTime						${{ {} }}				422    	This property must be an simple value, not an object														Observation.effective.x
	$.effectiveDateTime						${{ [{}] }}				422    	This property must be an simple value, not an array															Observation.effective.x

	# invalid day
	$.effectiveDateTime						2020-09-00				422    	Not a valid date/time .Invalid date/time format: \"2020-09-00\".											Observation.effective.ofType.dateTime.
	$.effectiveDateTime						2020-09-32				422    	Not a valid date/time .Invalid date/time format: \"2020-09-32\".											Observation.effective.ofType.dateTime.
	$.effectiveDateTime						2020-09-dd				422    	Not a valid date/time .Invalid date/time format: \"2020-09-dd\".											Observation.effective.ofType.dateTime.
	
	# invalid month
	$.effectiveDateTime						2020-00-21				422    	Not a valid date/time .Invalid date/time format: \"2020-00-21\".											Observation.effective.ofType.dateTime.
	$.effectiveDateTime						2020-13-21				422    	Not a valid date/time .Invalid date/time format: \"2020-13-21\".											Observation.effective.ofType.dateTime.
	$.effectiveDateTime						2020-mm-21				422    	Not a valid date/time .Invalid date/time format: \"2020-mm-21\".											Observation.effective.ofType.dateTime.

	# invalid year
	$.effectiveDateTime						0000-09-21				422    	The value '0000-09-21' is outside the range of reasonable years - check for data entry error				Observation.effective.ofType.dateTime.
	$.effectiveDateTime						10000-09-21				422    	Not a valid date/time .Invalid date/time format: \"10000-09-21\".											Observation.effective.ofType.dateTime.
	$.effectiveDateTime						yyyy-09-21				422    	Not a valid date/time .Invalid date/time format: \"yyyy-09-21\".											Observation.effective.ofType.dateTime.

	# invalid Date format
	$.effectiveDateTime						21.09.2020				422    	Not a valid date/time .Invalid date/time format: \"21.09.2020\".											Observation.effective.ofType.dateTime.
	$.effectiveDateTime						${randstring}			422    	Not a valid date/time .Invalid date/time format: \"${randstring}".											Observation.effective.ofType.dateTime.
	$.effectiveDateTime						${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.effective.x.


009 Create Smoking Status (Invalid/Missing 'valueCodeableConcept')
	[Documentation]     1. *Create* new an EHR record\n\n 
	...                 2. *LOAD* _create-smoking-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``valueCodeableConcept`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create smoking status with ehr reference
    [Tags]              valueCodeableConcept


	# FIELD/PATH								VALUE					HTTP	ERROR MESSAGE																								Location
	# 																	CODE
	
	# missing valueCodeableConcept
	$.valueCodeableConcept						missing					422    	Index 0 out of bounds for length 0
	$.valueCodeableConcept						${EMPTY}				422    	This property must be an Object, not a primitive property													Observation.value.x.

	# wrong format
	$.valueCodeableConcept						${{ [] }}				422    	This property must be an Object, not an array																Observation.value.x.
	$.valueCodeableConcept						${{ {} }}				422    	Object must have some content																				Observation.value.x.
	$.valueCodeableConcept						${{ [{}] }}				422    	This property must be an Object, not an array																Observation.value.x.

	# missing coding
	$.valueCodeableConcept.coding 				missing					422    	Index 0 out of bounds for length 0
	$.valueCodeableConcept.coding				${EMPTY}				422    	This property must be an Array, not a primitive property													Observation.value.x..coding

	# invalid system
#	$.valueCodeableConcept.coding[0].system		missing					422    	This property must be an Array, not a primitive property													Observation.value.ofType.CodeableConcept..coding.0..system
	$.valueCodeableConcept.coding[0].system		${EMPTY}				422    	@value cannot be empty																						Observation.value.ofType.CodeableConcept..coding.0..system
#	$.valueCodeableConcept.coding[0].system		http://foobar.de		422    	This property must be an Array, not a primitive property													Observation.value.ofType.CodeableConcept..coding.0..system
	$.valueCodeableConcept.coding[0].system		${randstring}			422    	Coding.system must be an absolute reference, not a local reference											Observation.value.ofType.CodeableConcept..coding.0.
	$.valueCodeableConcept.coding[0].system		${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.value.x..coding.0..system

	# invalid code
#	$.valueCodeableConcept.coding[0].code		missing					422    	This property must be an Array, not a primitive property													Observation.value.ofType.CodeableConcept..coding.0..code
	$.valueCodeableConcept.coding[0].code		${EMPTY}				422    	@value cannot be empty																						Observation.value.ofType.CodeableConcept..coding.0..code
	$.valueCodeableConcept.coding[0].code		${randstring}			422    	Unexpected value: ${randstring}
	$.valueCodeableConcept.coding[0].code		${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.value.x..coding.0..code

	# invalid display
	$.valueCodeableConcept.coding[0].display	${EMPTY}				422    	@value cannot be empty																						Observation.value.ofType.CodeableConcept..coding.0..display
	$.valueCodeableConcept.coding[0].display	${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.value.x..coding.0..display

	# invalid text
	$.valueCodeableConcept.text					${EMPTY}				422    	@value cannot be empty																						Observation.value.ofType.CodeableConcept..text
	$.valueCodeableConcept.text					${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.value.x..text


#010 Create Smoking Status (Invalid/Missing 'valueCodeableConcept')
#	[Documentation]     1. *Create* new an EHR record\n\n 
#	...                 2. *LOAD* _create-smoking-status.json_\n\n
#	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
#	...                 4. *UPDATE* values for attribute ``valueCodeableConcept`` \n\n
#    ...                 5. *POST* example JSON to observation endpoint\n\n
#	...                 6. *VALIDATE* the response status \n\n
#    ...                 7. *VALIDATE* outcome against diagnostic text & location
#	[Template]			create smoking status with ehr reference add attribute
#    [Tags]              valueCodeableConcept
#
#	# FIELD/PATH								VALUE					HTTP	ERROR MESSAGE																								Location
#	# 																	CODE



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

create smoking status with ehr reference
	[Arguments]         ${json_path}        ${value}                 ${http_status_code}
	...					${error_message}    ${location}=${None}

						ehr.create new ehr    000_ehr_status.json
	${payload}=    		generate payload from example json    ${json_path}    ${value}
						observation.POST /Observation    Smoking Status    ${payload}
						observation.validate response - 422 (with error message NEW)     ${http_status_code}
						...															     ${error_message}
						...															     ${location}


create smoking status w/o ehr reference    
	[Arguments]         ${json_path}        ${value}                ${http_status_code}
	...					${error_message}    ${location}=${None}

	${fake_ehr_ref}=	Evaluate    str(uuid.uuid4())    uuid
						Set Test Variable    ${subject_id}    ${fake_ehr_ref}
	${payload}=    		generate payload from example json    ${json_path}    ${value}
						observation.POST /Observation    Smoking Status    ${payload}
						observation.validate response - 422 (with error message NEW)      ${http_status_code}
						...															      ${error_message}
						...															      ${location}


generate payload from example json
	[Documentation]		Generates actual request payload using example json as a starting point.
	[Arguments]			${json_path}    ${value}

	${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/create-smoking-status.json
                        Update Value To Json    ${payload}    $.subject.identifier.value    ${subject_id}
						Delete Object From Json    ${payload}    $.text

						# comment: delete field/object that has value 'missing' in test case table 
						Run Keyword And Return If   $value=="missing"
						...    	Run Keyword    Delete Object From Json    ${payload}    ${json_path}

						# comment: set value from data table in test case
						Update Value To Json            ${payload}    ${json_path}    ${value}
						Output Debug Info To Console    ${payload}

	[Return]			${payload}

