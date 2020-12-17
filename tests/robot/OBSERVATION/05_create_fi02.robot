# Copyright (c) 2020 Wladislaw Wagner (Vitasystems GmbH)
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

Force Tags              create    fi02    invalid



*** Variables ***
${profile url}			https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/inhaled-oxygen-concentration




*** Test Cases ***
001 Create FiO2 (Invalid/Missing EHR reference/subject)
	[Documentation]		1. *CREATE* random UUID to serve as non-existent EHR reference\n\n
	...					2. *REPLACE* {{patientID}} in example JSON with fake EHR reference\n\n
	...					3. *GENERATE* (invalid) payload for FiO2 profile\n\n
	...					   based on example JSON and data in TC table\n\n
	...					4. *POST* FiO2 payload to /Observation endpoint\n\n
	...					
	...					*NOTE:* use Regular Expressions to replace braces (),[] as described here:
	...          		https://json-schema.org/understanding-json-schema/reference/regular_expressions.html#example

	[Template]			Create FiO2 w/o ehr reference
	[Tags]				Subject
	#												HTTP	
	# FIELD/PATH 					VALUE			CODE	ERROR MESSAGE
	$.subject.identifier.value		missing			422		mii-reference-1: Either reference.reference XOR reference.identifier exists ..this.reference.exists.. xor ..this.identifier.value.exists.. and .this.identifier.system.exists
	$.subject.identifier.system		missing			422		mii-reference-1: Either reference.reference XOR reference.identifier exists ..this.reference.exists.. xor ..this.identifier.value.exists.. and .this.identifier.system.exists
	$.subject.identifier.value		foobar			422		EhrId not found for subject 'foobar'
	$.subject.identifier.value		${EMPTY}		422		@value cannot be empty    Observation.subject.identifier.value
	$.subject.identifier.value		${{ [] }}		422		This property must be an simple value, not an array    Observation.subject.identifier.value
	$.subject.identifier.value		${{ {} }}		422		This property must be an simple value, not an object    Observation.subject.identifier.value
	$.subject.identifier.value		${123}			422		Error parsing JSON: the primitive value must be a string    Observation.subject.identifier.value
	$.subject.identifier			missing			422		Object must have some content    Observation.subject
    $.subject.identifier			${EMPTY}		422		This property must be an Object, not a primitive property    Observation.subject.identifier
    $.subject.identifier			${{ [] }}		422		This property must be an Object, not an array    Observation.subject.identifier
    $.subject.identifier			${{ {} }}		422		Object must have some content    Observation.subject.identifier
    $.subject.identifier			${123}			422		This property must be an Object, not a primitive property    Observation.subject.identifier
    $.subject						missing			422		Observation.subject: minimum required = 1, but only found 0 .from ${profile url}
	$.subject						${EMPTY}		422		This property must be an Object, not a primitive property    Observation.subject
    $.subject						${{ [] }}		422		This property must be an Object, not an array    Observation.subject
    $.subject						${{ {} }}		422		Object must have some content    Observation.subject
    $.subject						${123}			422		This property must be an Object, not a primitive property    Observation.subject

	# comment: random uuid																			 regex for uuid
	$.subject.identifier.value    ${{str(uuid.uuid4())}}    422    EhrId not found for subject '([0-9a-f]{32}|[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})'
	

002 Create FiO2 (Invalid/Missing 'resourceType')
	[Documentation]		1. *CREATE* an EHR record\n\n
	...					2. *REPLACE* {{patientID}} in example JSON with EHR reference\n\n
	...					3. *GENERATE* (invalid) payload for FiO2 profile\n\n
	...					   based on example JSON and data in TC table\n\n
	...					4. *POST* FiO2 payload to /Observation endpoint\n\n
	
	[Template]			Create FiO2 with ehr reference
    [Tags]          	resourceType
	#									HTTP	
	# FIELD/PATH 		VALUE			CODE	ERROR MESSAGE
	$.resourceType		missing			422		Unable to find resourceType property
	$.resourceType		foobar			422		This does not appear to be a FHIR resource .unknown name 'foobar'.
	$.resourceType		${EMPTY}		422		This does not appear to be a FHIR resource .unknown name ''.
	$.resourceType		${EMPTY}		422		This does not appear to be a FHIR resource .unknown name ''.
    $.resourceType		${123}			422		This does not appear to be a FHIR resource .unknown name '123'.


003 Create FiO2 (Invalid/Missing 'ID')
	[Documentation]		1. *CREATE* an EHR record\n\n
	...					2. *REPLACE* {{patientID}} in example JSON with EHR reference\n\n
	...					3. *GENERATE* (invalid) payload for FiO2 profile\n\n
	...					   based on example JSON and data in TC table\n\n
	...					4. *POST* FiO2 payload to /Observation endpoint\n\n
	
	[Template]			Create FiO2 with ehr reference
    [Tags]   			ID

	# $.id				missing			201		
	$.id				${EMPTY}		422		@value cannot be empty		


004 Create FiO2 (Invalid/Missing 'identifier')
	[Documentation]		1. *CREATE* an EHR record\n\n
	...					2. *REPLACE* {{patientID}} in example JSON with EHR reference\n\n
	...					3. *GENERATE* (invalid) payload for FiO2 profile\n\n
	...					   based on example JSON and data in TC table\n\n
	...					4. *POST* FiO2 payload to /Observation endpoint\n\n
	
	[Template]			Create FiO2 with ehr reference
    [Tags]   			identifier
	#									   HTTP	
	# FIELD/PATH 			VALUE		   CODE		ERROR MESSAGE
	$.identifier			missing		   422     Observation.identifier:analyseBefundCode: minimum required = 1, but only found 0 .from ${profile url}
	$.identifier			${EMPTY}	   422     This property must be an Array, not a primitive property
	$.identifier			${123}		   422     This property must be an Array, not a primitive property
	$.identifier			foobar		   422     This property must be an Array, not a primitive property
	$.identifier			foobar baz	   422     This property must be an Array, not a primitive property
	$.identifier			${{ [] }}	   422     Observation.identifier:analyseBefundCode: minimum required = 1, but only found 0 .from ${profile url}
	$.identifier			${{ [{}] }}	   422     Observation.identifier:analyseBefundCode: minimum required = 1, but only found 0 .from ${profile url}
	$.identifier			${None}		   422     This property must be an Array, not a Null
	$.identifier[0].type	missing		   422     Observation.identifier:analyseBefundCode: minimum required = 1, but only found 0 .from ${profile url}
	$.identifier[0].type	${EMPTY}	   422     This property must be an Object, not a primitive property
	$.identifier[0].type	${123}		   422     This property must be an Object, not a primitive property
	$.identifier[0].type	foobar		   422     This property must be an Object, not a primitive property
	$.identifier[0].type	${{ [] }}	   422     This property must be an Object, not an array
	$.identifier[0].type	${{ {} }}	   422     Object must have some content
	$.identifier[0].type	${None}		   422     This property must be an Object, not null
	
	$.identifier[0].type.coding    			 missing		   422    Object must have some content
	$.identifier[0].type.coding[0].system	 missing    	   422    A code with no system has no defined meaning. A system should be provided
	$.identifier[0].type.coding[0].system	 ${None}    	   422    This property must be an simple value, not null
	$.identifier[0].type.coding[0].system    ${EMPTY}   	   422    @value cannot be empty
	$.identifier[0].type.coding[0].system    http://foo bar    422    URI values cannot have whitespace.'http://foo bar'
	$.identifier[0].type.coding[0].system    http://foo    	   422    This element does not match any known slice defined in the profile ${profile url}
	$.identifier[0].type.coding[0].code    	 missing    	   422    This element does not match any known slice defined in the profile ${profile url}
	$.identifier[0].type.coding[0].code    	 ${None}    	   422    This property must be an simple value, not null
	$.identifier[0].type.coding[0].code    	 ${EMPTY}    	   422    @value cannot be empty
	$.identifier[0].type.coding[0].code    	 ${123}    	   	   422    Error parsing JSON: the primitive value must be a string
	$.identifier[0].type.coding[0].code    	 foo    	       422    This element does not match any known slice defined in the profile ${profile url}
	$.identifier[0].system					 missing		   422	   Observation.identifier:analyseBefundCode.system: minimum required = 1, but only found 0				
	$.identifier[0].value					 missing		   422	   Observation.identifier:analyseBefundCode.value: minimum required = 1, but only found 0				
	$.identifier[0].assigner				 missing		   422	   Observation.identifier:analyseBefundCode.assigner: minimum required = 1, but only found 0				


005 Create FiO2 (Invalid/Missing 'meta')
	[Documentation]		1. *CREATE* an EHR record\n\n
	...					2. *REPLACE* {{patientID}} in example JSON with EHR reference\n\n
	...					3. *GENERATE* (invalid) payload for FiO2 profile\n\n
	...					   based on example JSON and data in TC table\n\n
	...					4. *POST* FiO2 payload to /Observation endpoint\n\n

	[Template]			Create FiO2 with ehr reference
    [Tags]             	meta
	#												HTTP	
	# FIELD/PATH 		VALUE						CODE	ERROR MESSAGE
	$.meta				missing						422    	Default profile is not supported for Observation. One of the following profiles is expected: .https://.*
	$.meta.profile		missing						422    	Object must have some content
	$.meta.profile    	${{ ["invalid_url"] }}		422    	Canonical URLs must be absolute URLs if they are not fragment references .invalid_url.
	$.meta.profile    	${{ ["http://wrong.url"] }}   422  	Profile reference 'http://wrong.url' could not be resolved, so has not been checked
	$.meta.profile		${EMPTY}					422    	This property must be an Array, not a a primitive property
	$.meta.profile		${123}						422    	This property must be an Array, not a a primitive property
	$.meta.profile		${None}						422    	This property must be an Array, not a null
	
	# comment: the next one sets the value to an empty list/array []
	$.meta.profile		${{ [] }}					422    	Default profile is not supported for Observation. One of the following profiles is expected: .https://.*
	
	# comment: the next one sets value to an empty object {}
	$.meta.profile		${{ {} }}					422    	This property must be an Array, not a an object


005 Create FiO2 (Invalid/Missing 'meta') - BUG TRACE TEST
	[Documentation]		Belongs to TC 004! Remove separation when it's fixed!
	[Template]			Create FiO2 with ehr reference
	[Tags]				TODO: ADD GITHUB ISSUE    not-ready
	# NOT-READY, RETURNS --> ERROR 500
	# comment: sets value to an array with empty object
	$.meta.profile		${{ [{}] }}					422    	This property should contain list of strings


006 Create FiO2 (Invalid/Missing 'code')
	[Documentation]		1. *CREATE* an EHR record\n\n
	...					2. *REPLACE* {{patientID}} in example JSON with EHR reference\n\n
	...					3. *GENERATE* (invalid) payload for FiO2 profile\n\n
	...					   based on example JSON and data in TC table\n\n
	...					4. *POST* FiO2 payload to /Observation endpoint\n\n
	
	[Template]			Create FiO2 with ehr reference
    [Tags]              code
	#											HTTP	
	# FIELD/PATH 				VALUE			CODE	ERROR MESSAGE
	$.code						missing			422    	Observation.code: minimum required = 1, but only found 0 .from ${profile url}
	
	# comment: code:loinc
	$.code.coding[0].code		missing			422    	Observation.code.coding:loinc: minimum required = 1, but only found 0 .from ${profile url}
	$.code.coding[0].code		${EMPTY}		422    	Observation.code.coding:loinc: minimum required = 1, but only found 0 .from ${profile url}
	$.code.coding[0].code		${None}			422    	Observation.code.coding:loinc: minimum required = 1, but only found 0 .from ${profile url}
	$.code.coding[0].code		${123}			422    	Observation.code.coding:loinc: minimum required = 1, but only found 0 .from ${profile url}
	$.code.coding[0].code		foo			 	422    	Observation.code.coding:loinc: minimum required = 1, but only found 0 .from ${profile url}
	
	$.code.coding[0].system		missing			422    	Observation.code.coding:loinc: minimum required = 1, but only found 0 .from ${profile url}
	$.code.coding[0].system		${EMPTY}		422    	Observation.code.coding:loinc: minimum required = 1, but only found 0 .from ${profile url}
	$.code.coding[0].system		${None}			422    	Observation.code.coding:loinc: minimum required = 1, but only found 0 .from ${profile url}
	$.code.coding[0].system		${123}			422    	Observation.code.coding:loinc: minimum required = 1, but only found 0 .from ${profile url}
	$.code.coding[0].system		foo			 	422    	Observation.code.coding:loinc: minimum required = 1, but only found 0 .from ${profile url}
	$.code.coding[0].system		http://foo	 	422    	Observation.code.coding:loinc: minimum required = 1, but only found 0 .from ${profile url}
	

007 Create FiO2 (Invalid/Missing 'category')
	[Documentation]		1. *CREATE* an EHR record\n\n
	...					2. *REPLACE* {{patientID}} in example JSON with EHR reference\n\n
	...					3. *GENERATE* (invalid) payload for FiO2 profile\n\n
	...					   based on example JSON and data in TC table\n\n
	...					4. *POST* FiO2 payload to /Observation endpoint\n\n

	[Template]			Create FiO2 with ehr reference
    [Tags]    			category
	#														HTTP	
	# FIELD/PATH 						VALUE				CODE	ERROR MESSAGE				... LOCATION
	$.category						  	missing				422    	Observation.category: minimum required = 1, but only found 0 .from ${profile url}
	$.category						  	${{ [] }}			422    	Array cannot be empty - the property should not be present if it has no values
	$.category						  	${{ {} }}			422    	This property must be an Array, not an Object
	$.category						  	${{ [{}] }}			422    	Object must have some content
	$.category[0].coding    		  	missing				422    	Object must have some content
	$.category[0].coding    		  	${EMPTY}			422    	This property must be an Array, not a primitive property
	
	# comment: category:loinc-observation
	$.category[0].coding[0].code      	missing    			422    	This element does not match any known slice defined in the profile ${profile url}
	$.category[0].coding[0].code      	${EMPTY}    		422    	@value cannot be empty
	$.category[0].coding[0].code      	foobar    			422    	This element does not match any known slice defined in the profile ${profile url}
	$.category[0].coding[0].system    	missing    			422    	Observation.category.coding:loinc-observation: minimum required = 1, but only found 0 .from ${profile url}
	$.category[0].coding[0].system    	${EMPTY}    		422    	@value cannot be empty    								Observation.category.0..coding.0..system
	$.category[0].coding[0].system    	${None}    			422    	This property must be an simple value, not null    		Observation.category.0..coding.0..system
	$.category[0].coding[0].system    	http://foo bar    	422   	URI values cannot have whitespace.'http://foo bar'    	Observation.category.0..coding.0..system
	$.category[0].coding[0].system    	http://foo    		422		This element does not match any known slice defined in the profile ${profile url}

	# comment: category:observation-category
	$.category[0].coding[1].code      	missing    			422    	This element does not match any known slice defined in the profile ${profile url}
	$.category[0].coding[1].code      	${EMPTY}    		422    	@value cannot be empty
	$.category[0].coding[1].code      	foobar    			422    	This element does not match any known slice defined in the profile ${profile url}
	$.category[0].coding[1].code      	${123}    			422    	This element does not match any known slice defined in the profile ${profile url}
	$.category[0].coding[1].system    	missing    			422    	This element does not match any known slice defined in the profile ${profile url}
	$.category[0].coding[1].system    	${EMPTY}    		422    	Observation.category.coding:observation-category: minimum required = 1, but only found 0 .from ${profile url}
	$.category[0].coding[1].system    	${None}    			422    	This property must be an simple value, not null    		Observation.category.0..coding.1..system
	$.category[0].coding[1].system    	http://foo bar    	422  	Observation.category.coding:observation-category: minimum required = 1, but only found 0 .from ${profile url}
	$.category[0].coding[1].system    	http://foo    		422    	This element does not match any known slice defined in the profile ${profile url}


007 Create FiO2 (Invalid/Missing 'category') - BUG TRACE TEST
	[Documentation]		Belongs to TC 004! Remove separation when it's fixed!
	[Template]			Create FiO2 with ehr reference
	[Tags]				TODO: ADD GITHUB ISSUE    not-ready
	#														HTTP	
	# FIELD/PATH 						VALUE				CODE	ERROR MESSAGE
	# comment: category:blood-gas-studies
	$.category[0].coding[2].code      	missing    			422    	Observation.category.coding:blood-gas-studies: minimum required = 1, but only found 0 .from ${profile url}
	$.category[0].coding[2].code      	${EMPTY}    		422    	Observation.category.coding:blood-gas-studies: minimum required = 1, but only found 0 .from ${profile url}
	$.category[0].coding[2].code      	${None}    			422    	Observation.category.coding:blood-gas-studies: minimum required = 1, but only found 0 .from ${profile url}
	$.category[0].coding[2].code      	foobar    			422    	Observation.category.coding:blood-gas-studies: minimum required = 1, but only found 0 .from ${profile url}
	$.category[0].coding[2].code      	${123}    			422    	Observation.category.coding:blood-gas-studies: minimum required = 1, but only found 0 .from ${profile url}
	$.category[0].coding[2].system    	missing    			422    	Observation.category.coding:blood-gas-studies: minimum required = 1, but only found 0 .from ${profile url}
	$.category[0].coding[2].system    	${EMPTY}    		422    	Observation.category.coding:blood-gas-studies: minimum required = 1, but only found 0 .from ${profile url}
	$.category[0].coding[2].system    	${None}    			422    	Observation.category.coding:blood-gas-studies: minimum required = 1, but only found 0 .from ${profile url}
	$.category[0].coding[2].system    	http://foo bar		422    	Observation.category.coding:blood-gas-studies: minimum required = 1, but only found 0 .from ${profile url}
	$.category[0].coding[2].system    	http://foo    		422    	Observation.category.coding:blood-gas-studies: minimum required = 1, but only found 0 .from ${profile url}


008 Create FiO2 (Invalid/Missing 'effectiveDateTime')
	[Documentation]		1. *CREATE* an EHR record\n\n
	...					2. *REPLACE* {{patientID}} in example JSON with EHR reference\n\n
	...					3. *GENERATE* (invalid) payload for FiO2 profile\n\n
	...					   based on example JSON and data in TC table\n\n
	...					4. *POST* FiO2 payload to /Observation endpoint\n\n

	[Template]			Create FiO2 with ehr reference
    [Tags]  			DateTime
	#											HTTP	
	# FIELD/PATH 				VALUE			CODE	ERROR MESSAGE
	$.effectiveDateTime 		missing			422		Observation.effective.x.: minimum required = 1, but only found 0 .from ${profile url}
	$.effectiveDateTime 		${EMPTY}		422		@value cannot be empty    Observation.effective.ofType.dateTime
	$.effectiveDateTime 		${None}			422		Observation.effective.x.: minimum required = 1, but only found 0 .from ${profile url}
	$.effectiveDateTime 		${123}			422		Error parsing JSON: the primitive value must be a string    Observation.effective.x
	$.effectiveDateTime 		foobar			422		Not a valid date/time .Invalid date/time format: "foobar"
	$.effectiveDateTime 		2020-13-21		422		Not a valid date/time .Invalid date/time format: "2020-13-21"


009 Create FiO2 (Invalid/Missing 'valueQuantity')
	[Documentation]		1. *CREATE* an EHR record\n\n
	...					2. *REPLACE* {{patientID}} in example JSON with EHR reference\n\n
	...					3. *GENERATE* (invalid) payload for FiO2 profile\n\n
	...					   based on example JSON and data in TC table\n\n
	...					4. *POST* FiO2 payload to /Observation endpoint\n\n

	[Template]			Create FiO2 with ehr reference
    [Tags]              valueQuantity
	#											HTTP	
	# FIELD/PATH 				VALUE			CODE	ERROR MESSAGE
	$.valueQuantity			  	missing			422    	Observation.value.x.: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity			  	${EMPTY}		422    	Observation.value.x.: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity			  	${None}			422    	Observation.value.x.: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity			  	${{ {} }}		422    	Observation.value.x.:valueQuantity.value: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity			  	${{ {} }}		422    	Observation.value.x.:valueQuantity.unit: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity			  	${{ {} }}		422    	Observation.value.x.:valueQuantity.system: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity			  	${{ {} }}		422    	Observation.value.x.:valueQuantity.code: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity.value	  	missing			422    	Observation.value.x.:valueQuantity.value: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity.unit	  	missing			422    	Observation.value.x.:valueQuantity.unit: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity.system	  	missing			422    	Observation.value.x.:valueQuantity.system: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity.code	  	missing			422    	Observation.value.x.:valueQuantity.code: minimum required = 1, but only found 0 .from ${profile url}
	
	$.valueQuantity.value	  	${EMPTY}		422    	Error parsing JSON: the primitive value must be a number
	$.valueQuantity.value	  	${None}			422    	This property must be an simple value, not null
	$.valueQuantity.value	  	113				422    	Error parsing JSON: the primitive value must be a number
	$.valueQuantity.value	  	${101}			422    	.*value is not within interval, expected:0.0 <= 101.0 <= 100.0.*Bad Request.*
	$.valueQuantity.value	  	${100.09}		422    	.*value is not within interval, expected:0.0 <= 100.09 <= 100.0.*Bad Request.*
	$.valueQuantity.value	  	${-1}			422    	.*value is not within interval, expected:0.0 <= -1.0 <= 100.0.*Bad Request.*
	$.valueQuantity.value	  	100,7			422    	The value '100,7' is not a valid decimal    Observation.value.ofType.Quantity..value
	$.valueQuantity.value	  	foobar			422    	Error parsing JSON: the primitive value must be a number
	
	$.valueQuantity.unit	  	${EMPTY}		422    	@value cannot be empty    Observation.value.ofType.Quantity..unit
	$.valueQuantity.unit	  	${None}			422    	Observation.value.x.:valueQuantity.unit: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity.unit	  	${123}			422    	Error parsing JSON: the primitive value must be a string
	
	$.valueQuantity.system	  	${EMPTY}		422    	@value cannot be empty    Observation.value.ofType.Quantity..system
	$.valueQuantity.system	  	${None}			422    	Observation.value.x.:valueQuantity.system: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity.system	  	foobar			422    	Value is 'foobar' but must be 'http://unitsofmeasure.org'
	$.valueQuantity.system	  	${123}			422    	Error parsing JSON: the primitive value must be a string
	
	$.valueQuantity.code	  	${EMPTY}		422    	@value cannot be empty    Observation.value.ofType.Quantity..code
	$.valueQuantity.code	  	${None}			422    	Observation.value.x.:valueQuantity.code: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity.code	  	${123}			422    	Error parsing JSON: the primitive value must be a string
	$.valueQuantity.code	  	foobar			422    	Value is 'foobar' but must be '%'





*** Keywords ***
Create FiO2 with ehr reference
	[Documentation]		1. *CREATE* an EHR record\n\n
	...					2. *REPLACE* {{patientID}} in example JSON with EHR reference\n\n
	...					3. *GENERATE* (invalid) payload for FiO2 profile\n\n
	...					   based on example JSON and data in TC table\n\n
	...					4. *POST* FiO2 payload to /Observation endpoint\n\n

	[Arguments]         ${json_path}  ${value}  ${http_status_code}  ${error_message}  ${location}=${None}

						ehr.create new ehr    000_ehr_status.json
	${payload}=    		generate payload from example json    ${json_path}    ${value}
						observation.POST /Observation    FiO2    ${payload}
						observation.validate response - 422 (with error message NEW)    ${http_status_code}
						...																${error_message}
						...																${location}


Create FiO2 w/o ehr reference
	[Documentation]		1. *CREATE* random UUID to serve as non-existent EHR reference\n\n
	...					2. *REPLACE* {{patientID}} in example JSON with fake EHR reference\n\n
	...					3. *GENERATE* (invalid) payload for FiO2 profile\n\n
	...					   based on example JSON and data in TC table
	...					4. *POST* FiO2 payload to /Observation endpoint\n\n

	[Arguments]         ${json_path}  ${value}  ${http_status_code}  ${error_message}  ${location}=${None}

	${fake_ehr_ref}=	Evaluate    str(uuid.uuid4())    uuid
						Set Test Variable    ${subject_id}    ${fake_ehr_ref}
	${payload}=    		generate payload from example json    ${json_path}    ${value}
						observation.POST /Observation    FiO₂    ${payload}
						observation.validate response - 422 (with error message NEW)    ${http_status_code}
						...																${error_message}
						...																${location}


generate payload from example json
	[Documentation]		- Generates actual request payload using example JSON as a starting point
	...					- Removes/modifies fields in example JSON based on data in TC table

	[Arguments]			${json_path}    ${value}

	${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/create-fio2.json
                        Update Value To Json    ${payload}    $.subject.identifier.value    ${subject_id}
						Delete Object From Json    ${payload}    $.text

						# comment: delete field/object that has value 'missing' in test case table 
						Run Keyword And Return If   $value=="missing"
						...    	Run Keyword    Delete Object From Json    ${payload}    ${json_path}

						# comment: set value from data table in test case
						Update Value To Json    ${payload}    ${json_path}    ${value}
						Output Debug Info To Console    ${payload}

	[Return]			${payload}
