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
	

002 Create Blood Pressure (Invalid/Missing 'resourceType')
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


002 Create Blood Pressure (Invalid/Missing 'ID')
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


003 Create Blood Pressure (Invalid/Missing 'meta')
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


004 Create Blood Pressure (Invalid/Missing 'Status')
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


005 Create Smoking Status (Invalid/Missing 'category')
	[Documentation]     1. *Create* new an EHR record\n\n 
	...                 2. *LOAD* _create-smoking-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``Category`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create smoking status with ehr reference
    [Tags]              category    xxx

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


#006 Create Blood Pressure (Invalid/Missing 'code')
#	[Documentation]     1. *Create* new an EHR record\n\n 
#	...                 2. *LOAD* _create-smoking-status.json_\n\n
#	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID: ${subject_id}_ which was created in EHR record\n\n
#	...                 4. *UPDATE* values for attribute ``code`` \n\n
#   ...                 5. *POST* example JSON to observation endpoint\n\n
#	...                 6. *VALIDATE* the response status \n\n
#   ...                 7. *VALIDATE* outcome against diagnostic text & location
#	[Template]			create blood pressure with ehr reference
#   [Tags]              code    todo
#
#	# FIELD/PATH		VALUE			ISSUE	HTTP	ERROR MESSAGE
#	# 									INDEX	CODE
#	$.code				missing			0		422    	Observation.code: minimum required = 1, but only found 0 .from ${${smoking_status-url}}
	


*** Keywords ***
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