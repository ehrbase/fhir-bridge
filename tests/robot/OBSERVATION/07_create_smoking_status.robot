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

Force Tags              create    smoking-status    invalid



*** Variables ***
${smoking_status-url}			https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/smoking-status	




*** Test Cases ***

001 Create Smoking Status (Invalid/Missing 'Subject')
    [Documentation]    1. *LOAD* _create-smoking-status.json_ \n\n
	...                2. *UPDATE* values for attribute ``Subject`` \n\n
    ...                3. *POST* example JSON to observation endpoint \n\n
	...                4. *VALIDATE* the response status \n\n
    ...                5. *VALIDATE* outcome against diagnostic text & location

	[Template]			create smoking status w/o ehr reference

	[Tags]              subject    xxx

	# FIELD/PATH					VALUE					HTTP	ERROR MESSAGE													Location
	# 														CODE

	# invalid cases for value
    $.subject.identifier.value		missing					422		 Subject identifier is required
    $.subject.identifier.value		foobar					422		 EhrId not found for subject 'foobar'
    $.subject.identifier.value		${EMPTY}				422		 @value cannot be empty                                        Observation.subject.identifier.value
    $.subject.identifier.value		${{ [] }}				422		 This property must be an simple value, not an array           Observation.subject.identifier.value
    $.subject.identifier.value		${{ {} }}				422		 This property must be an simple value, not an object          Observation.subject.identifier.value
    $.subject.identifier.value		${123}					422		 Error parsing JSON: the primitive value must be a string      Observation.subject.identifier.value

		# invalid cases for system
    $.subject.identifier.system		foobar					422		Identifier.system must be an absolute reference, not a local reference
    $.subject.identifier.system		${EMPTY}				422		@value cannot be empty                                         Observation.subject.identifier.system
    $.subject.identifier.system		${{ [] }}				422		 This property must be an simple value, not an array           Observation.subject.identifier.system
    $.subject.identifier.system		${{ {} }}				422		 This property must be an simple value, not an object          Observation.subject.identifier.system
    $.subject.identifier.system		${123}					422		 Error parsing JSON: the primitive value must be a string      Observation.subject.identifier.system

	# invalid cases for identifier
    $.subject.identifier			missing					422		 Object must have some content                                 Observation.subject
    $.subject.identifier			${EMPTY}				422		 This property must be an Object, not a primitive property     Observation.subject.identifier
    $.subject.identifier			${{ [] }}				422		 This property must be an Object, not an array                 Observation.subject.identifier
    $.subject.identifier			${{ {} }}				422		 Object must have some content                                 Observation.subject.identifier
    $.subject.identifier			${123}					422		 This property must be an Object, not a primitive property     Observation.subject.identifier

	# invalid cases for subject
    $.subject						missing					422		 Observation.subject: minimum required = 1, but only found 0 .from ${smoking_status-url}
    $.subject						${EMPTY}				422		 This property must be an Object, not a primitive property     Observation.subject
    $.subject						${{ [] }}				422		 This property must be an Object, not an array                 Observation.subject
    $.subject						${{ {} }}				422		 Object must have some content                                 Observation.subject
    $.subject						${123}					422		 This property must be an Object, not a primitive property     Observation.subject
	
	# comment: random uuid																			 regex for uuid
    $.subject.identifier.value    ${{str(uuid.uuid4())}}    422     EhrId not found for subject
	

001 Create Blood Pressure (Invalid/Missing 'resourceType')
	[Documentation]     TODO
	...					NOTE: use Regular Expressions to replace braces () as described here:
	...          		https://json-schema.org/understanding-json-schema/reference/regular_expressions.html#example
	[Template]			create blood pressure with ehr reference
    [Tags]          	

	# FIELD/PATH		VALUE			ISSUE	HTTP	ERROR MESSAGE
	# 									INDEX	CODE	
	$.resourceType		missing			0		422		Unable to find resourceType property
	$.resourceType		foobar			0		422		This does not appear to be a FHIR resource .unknown name 'foobar'.
	$.resourceType		${EMPTY}		0		422		This does not appear to be a FHIR resource .unknown name ''.
    $.resourceType		${123}			0		422		This does not appear to be a FHIR resource .unknown name '123'.


002 Create Blood Pressure (Invalid/Missing 'meta')
	[Documentation]     TODO
	[Template]			create blood pressure with ehr reference
    [Tags]             	

	# FIELD/PATH		VALUE						ISSUE	HTTP	ERROR MESSAGE
	# 												INDEX	CODE
	$.meta			missing							0		422    	Default profile is not supported for Observation. One of the following profiles is expected: .https://.*
	$.meta.profile	missing							0		422    	Object must have some content
	$.meta.profile    ${{ ["invalid_url"] }}		0   	422    	Canonical URLs must be absolute URLs if they are not fragment references .invalid_url.
	$.meta.profile    ${{ ["http://wrong.url"] }}	0    	422    	Profile reference 'http://wrong.url' could not be resolved, so has not been checked
	$.meta.profile	${EMPTY}						0		422    	This property must be an Array, not a a primitive property
	
	# comment: the next one sets the value to an empty list/array []
	$.meta.profile		${{ [] }}					0		422    	Default profile is not supported for Observation. One of the following profiles is expected: .https://.*
	
	# comment: the next one sets value to an empty object {}
	$.meta.profile	${{ {} }}						0		422    	This property must be an Array, not a an object



003 Create Blood Pressure (Invalid/Missing 'code')
	[Documentation]     TODO
	[Template]			create blood pressure with ehr reference
    [Tags]              

	# FIELD/PATH		VALUE			ISSUE	HTTP	ERROR MESSAGE
	# 									INDEX	CODE
	$.code				missing			0		422    	Observation.code: minimum required = 1, but only found 0 .from ${${smoking_status-url}}
	

004 Create Blood Pressure (Invalid/Missing 'category:VSCat')
	[Documentation]     TODO
	[Template]			create blood pressure with ehr reference
    [Tags]              

	# FIELD/PATH							VALUE			ISSUE	HTTP	ERROR MESSAGE    ... LOCATION
	# 														INDEX	CODE
	$.category								missing			0		422    	Observation.category: minimum required = 1, but only found 0 .from ${${smoking_status-url}}
	$.category								${{ [] }}		0		422    	Array cannot be empty - the property should not be present if it has no values
	$.category								${{ {} }}		0		422    	This property must be an Array, not an Object
	$.category								${{ [{}] }}		0		422    	Object must have some content

	$.category[0].coding    				missing			0		422    	Object must have some content
	$.category[0].coding    				${EMPTY}		0		422    	This property must be an Array, not a primitive property
	
	$.category[0].coding[0].code    		missing    		0    	422    	This element does not match any known slice defined in the profile ${${smoking_status-url}}
	$.category[0].coding[0].code    		${EMPTY}    	2    	422    	@value cannot be empty
	$.category[0].coding[0].code    		foobar    		0    	422    	This element does not match any known slice defined in the profile ${${smoking_status-url}}
	...																		Observation.category[0]

	$.category[0].coding[0].system    		missing    		2    	422    	A code with no system has no defined meaning. A system should be provided
	...																		Observation.category[0].coding[0]

	$.category[0].coding[0].system    		${EMPTY}    	3    	422    	@value cannot be empty
	...																		Observation.category[0].coding[0].system

	$.category[0].coding[0].system    		foobar    		2    	422    	Coding.system must be an absolute reference, not a local reference
	...																		Observation.category[0].coding[0]
	
	$.category[0].coding[0].system    		http://foobar.de  0    	422    	This element does not match any known slice defined in the profile ${${smoking_status-url}}
	...																		Observation.category[0]




*** Keywords ***
create smoking status with ehr reference
	[Arguments]         ${json_path}        ${value}                 ${http_status_code}
	...					${error_message}    ${location}=${None}

						ehr.create new ehr    000_ehr_status.json
	${payload}=    		generate payload from example json    ${json_path}    ${value}
						observation.POST /Observation    Smoking Status    ${payload}
						observation.validate response - 422 (with error message) - new   ${http_status_code}
						...															     ${error_message}
						...															     ${location}


create smoking status w/o ehr reference    
	[Arguments]         ${json_path}        ${value}                ${http_status_code}
	...					${error_message}    ${location}=${None}

	${fake_ehr_ref}=	Evaluate    str(uuid.uuid4())    uuid
						Set Test Variable    ${subject_id}    ${fake_ehr_ref}
	${payload}=    		generate payload from example json    ${json_path}    ${value}
						observation.POST /Observation    Smoking Status    ${payload}
						observation.validate response - 422 (with error message) - new    ${http_status_code}
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