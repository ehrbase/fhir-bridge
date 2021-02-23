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
...															   Authorization=${AUTHORIZATION['Authorization']}
Documentation           *NOTE:* Use Regular Expressions to replace braces () as described here:
...                	    https://json-schema.org/understanding-json-schema/reference/regular_expressions.html#example \n\n
...						*Author:* Dave Petzold
Force Tags              observation_create    pregnancy-status    invalid    create



*** Variables ***
${pregnancy_status-url}			https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pregnancy-status
${randstring}                   foobar
${randinteger}                  ${12345}
${identifiersystem}             urn:ietf:rfc:3986
${identifiervalue}              urn:uuid:187e0c12-8dd2-67e2-1234-bf273c878281




*** Test Cases ***

001 Create Pregnancy Status (Invalid/Missing 'Subject')
    [Documentation]     1. *LOAD* _create-pregnancy-status.json_ \n\n
	...                 2. *UPDATE* values for attribute ``Subject`` \n\n
    ...                 3. *POST* example JSON to observation endpoint \n\n
	...                 4. *VALIDATE* the response status \n\n
    ...                 5. *VALIDATE* outcome against diagnostic text & location
	[Template]			create pregnancy status w/o ehr reference
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
    $.subject						missing							422		 Observation.subject: minimum required = 1, but only found 0 .from ${pregnancy_status-url}
    $.subject						${EMPTY}						422		 This property must be an Object, not a primitive property     					Observation.subject
    $.subject						${{ [] }}						422		 This property must be an Object, not an array                 					Observation.subject
    $.subject						${{ {} }}						422		 Object must have some content                                 					Observation.subject
    $.subject						${123}							422		 This property must be an Object, not a primitive property     					Observation.subject
	
	# comment: random uuid																			 regex for uuid
    $.subject.identifier.value    ${{str(uuid.uuid4())}}    		422     EhrId not found for subject
	

002 Create Pregnancy Status (Invalid/Missing 'resourceType')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-pregnancy-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``resourceType`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]		    create pregnancy status with ehr reference
    [Tags]          	resourceType

	# FIELD/PATH					VALUE							HTTP	ERROR MESSAGE																	Location
	# 																CODE
    $.resourceType					missing							422		Unable to find resourceType propert
    $.resourceType					${randstring}					422		This does not appear to be a FHIR resource .unknown name '${randstring}'.
    $.resourceType					${EMPTY}						422		This does not appear to be a FHIR resource .unknown name ''.
    $.resourceType					${randinteger}					422		This does not appear to be a FHIR resource .unknown name '${randinteger}'.


003 Create Pregnancy Status (Invalid/Missing 'ID')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-pregnancy-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``ID`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]		    create pregnancy status with ehr reference
    [Tags]          	ID

	# FIELD/PATH					VALUE							HTTP	ERROR MESSAGE																	Location
	# 																CODE
	$.id							${EMPTY}						422		@value cannot be empty															Observation.id												
	$.id							${randinteger}					422		Error parsing JSON: the primitive value must be a string						Observation.id


004 Create Pregnancy Status (Invalid/Missing 'meta')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-pregnancy-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID: ${subject_id}_ which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``meta`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create pregnancy status with ehr reference
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


005 Create Pregnancy Status (Invalid/Missing 'identifier')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-pregnancy-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID: ${subject_id}_ which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``identifier`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create pregnancy status with ehr reference
    [Tags]              identifier

	# FIELD/PATH					VALUE							HTTP	ERROR MESSAGE																									Location
	# 																CODE
	$.identifier					${EMPTY}						422    	This property must be an Array, not a primitive property														Observation.identifier
	$.identifier					${{ [] }}						422    	Array cannot be empty - the property should not be present if it has no values									Observation.identifier
	$.identifier					${{ {} }}						422    	This property must be an Array, not an Object																	Observation.identifier
	$.identifier					${{ [{}] }}						422    	Object must have some content																					Observation.identifier.0.

	# invalid system
	$.identifier[0].system			${EMPTY}					 	422	   	@value cannot be empty																							Observation.identifier.0..system				
	$.identifier[0].system			${randinteger}				 	422	   	Error parsing JSON: the primitive value must be a string														Observation.identifier.0..system
	$.identifier[0].system			${randstring}				 	422	   	Identifier.system must be an absolute reference, not a local reference											Observation.identifier.0.

	# invalid value
	$.identifier[0].value			${EMPTY}					 	422	   	@value cannot be empty																							Observation.identifier.0..value				
	$.identifier[0].value			${randinteger}				 	422	   	Error parsing JSON: the primitive value must be a string														Observation.identifier.0..value
	$.identifier[0].value			${randstring}				 	422	   	if identifier.system is ''urn:ietf:rfc:3986'', then the identifier.value must be a full URI						Observation.identifier.0.


006 Create Pregnancy Status (Invalid/Missing 'Status')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-pregnancy-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``Status`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]		    create pregnancy status with ehr reference
    [Tags]          	status

	# FIELD/PATH					VALUE							HTTP	ERROR MESSAGE																								Location
	# 																CODE
	$.status						missing							422		Observation.status: minimum required = 1, but only found 0 .from https://.*									Observation
	$.status						${EMPTY}						422		@value cannot be empty																						Observation.status
	$.status						${randinteger}					422		Error parsing JSON: the primitive value must be a string													Observation.status
	$.status						${randstring}					400		Failed to parse request body as JSON resource. Error was: .element=\"status\". Invalid attribute value \"foobar\": Unknown ObservationStatus code '${randstring}'


007 Create Pregnancy Status (Invalid/Missing 'category')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-pregnancy-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``Category`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create pregnancy status with ehr reference
    [Tags]              category

	# FIELD/PATH							VALUE					HTTP	ERROR MESSAGE																								Location
	# 																CODE

	# invalid category
	$.category								${{ [] }}				422    	Array cannot be empty - the property should not be present if it has no values								Observation.category
	$.category								${{ {} }}				422    	This property must be an Array, not an Object																Observation.category
	$.category								${{ [{}] }}				422    	Object must have some content																				Observation.category

	#invalid coding
	$.category[0].coding    				missing					422    	Object must have some content																				Observation.category.0.
	$.category[0].coding    				${EMPTY}				422    	This property must be an Array, not a primitive property													Observation.category.0..coding
	
	#invalid code 0
	$.category[0].coding[0].code    		${EMPTY}    	    	422    	@value cannot be empty																						Observation.category.0..coding.0..code
	$.category[0].coding[0].code    		${randinteger}	    	422    	Error parsing JSON: the primitive value must be a string													Observation.category.0..coding.0..code
	
	#invalid system 0
	$.category[0].coding[0].system    		${EMPTY}    	    	422    	@value cannot be empty																						Observation.category.0..coding.0..system
	$.category[0].coding[0].system    		${randstring}	    	422    	Coding.system must be an absolute reference, not a local reference											Observation.category.0..coding.0.
	$.category[0].coding[0].system    		${randinteger}	    	422    	Error parsing JSON: the primitive value must be a string													Observation.category.0..coding.0..system


008 Create Pregnancy Status (Invalid/Missing 'code')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-pregnancy-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``Code`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create pregnancy status with ehr reference
    [Tags]              code

	# FIELD/PATH							VALUE					HTTP	ERROR MESSAGE																								Location
	# 																CODE

	# invalid code
	$.code									missing					422    	Observation.code: minimum required = 1, but only found 0 .from ${pregnancy_status-url}						Observation
	$.code									${{ [] }}				422    	Observation.code: minimum required = 1, but only found 0 .from ${pregnancy_status-url}						Observation
	$.code									${{ {} }}				422    	Object must have some content																				Observation.code
	$.code									${{ [{}] }}				422    	This property must be an Object, not an array																Observation.code

	# invalid coding
	$.code.coding   	 					missing					422    	Observation.code.coding: minimum required = 1, but only found 0												Observation.code
	$.code.coding	    					${EMPTY}				422    	This property must be an Array, not a primitive property													Observation.code.coding

	# invalid Code Coding 0 System
    $.code.coding[0].system					missing					422    	A code with no system has no defined meaning. A system should be provided									Observation.code.coding.0.
	$.code.coding[0].system					${EMPTY}				422    	@value cannot be empty																						Observation.code.coding.0..system
	$.code.coding[0].system					http://foobar.de		422    	The pattern .system http://loinc.org, code 82810-3, and display 'null'. defined in the profile https://*	Observation.code
	$.code.coding[0].system					${randstring}			422    	Coding.system must be an absolute reference, not a local reference											Observation.code.coding.0.
	$.code.coding[0].system					${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.code.coding.0..system

	# invalid Code Coding 0 Code
    $.code.coding[0].code					missing					422    	The pattern .system http://loinc.org, code 82810-3, and display 'null'. defined in the profile https://*	Observation.code
	$.code.coding[0].code					${EMPTY}				422    	@value cannot be empty																						Observation.code.coding.0..code
	$.code.coding[0].code					${randstring}			422    	The pattern .system http://loinc.org, code 82810-3, and display 'null'. defined in the profile https://*	Observation.code
	$.code.coding[0].code					${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.code.coding.0..code

	# invalid Code Coding 0 Display
    $.code.coding[0].display				${EMPTY}				422    	@value cannot be empty																						Observation.code.coding.0..display
	$.code.coding[0].display				${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.code.coding.0..display


009 Create Pregnancy Status (Invalid/Missing 'effectiveDateTime')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-pregnancy-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``effectiveDateTime`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create pregnancy status with ehr reference
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


010 Create Pregnancy Status (Invalid/Missing 'valueCodeableConcept')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-pregnancy-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``valueCodeableConcept`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create pregnancy status with ehr reference
    [Tags]              valueCodeableConcept


	# FIELD/PATH								VALUE					HTTP	ERROR MESSAGE																								Location
	# 																	CODE
	
	# missing valueCodeableConcept
#	$.valueCodeableConcept						missing					422    	Index 0 out of bounds for length 0
	$.valueCodeableConcept						${EMPTY}				422    	This property must be an Object, not a primitive property													Observation.value.x.

	# wrong format
	$.valueCodeableConcept						${{ [] }}				422    	This property must be an Object, not an array																Observation.value.x.
	$.valueCodeableConcept						${{ {} }}				422    	Object must have some content																				Observation.value.x.
	$.valueCodeableConcept						${{ [{}] }}				422    	This property must be an Object, not an array																Observation.value.x.

	# missing coding
#	$.valueCodeableConcept.coding 				missing					422    	Index 0 out of bounds for length 0
	$.valueCodeableConcept.coding				${EMPTY}				422    	This property must be an Array, not a primitive property													Observation.value.x..coding

	# invalid system
	$.valueCodeableConcept.coding[0].system		${EMPTY}				422    	@value cannot be empty																						Observation.value.ofType.CodeableConcept..coding.0..system
#	$.valueCodeableConcept.coding[0].system		http://foobar.de		422    	This property must be an Array, not a primitive property													Observation.value.ofType.CodeableConcept..coding.0..system
	$.valueCodeableConcept.coding[0].system		${randstring}			422    	Coding.system must be an absolute reference, not a local reference											Observation.value.ofType.CodeableConcept..coding.0.
	$.valueCodeableConcept.coding[0].system		${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.value.x..coding.0..system

	# invalid code
	$.valueCodeableConcept.coding[0].code		${EMPTY}				422    	@value cannot be empty																						Observation.value.ofType.CodeableConcept..coding.0..code
	$.valueCodeableConcept.coding[0].code		${randstring}			422    	Status code ${randstring} is not supported
	$.valueCodeableConcept.coding[0].code		${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.value.x..coding.0..code

	# invalid display
	$.valueCodeableConcept.coding[0].display	${EMPTY}				422    	@value cannot be empty																						Observation.value.ofType.CodeableConcept..coding.0..display
	$.valueCodeableConcept.coding[0].display	${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.value.x..coding.0..display

	# invalid text
	$.valueCodeableConcept.text					${EMPTY}				422    	@value cannot be empty																						Observation.value.ofType.CodeableConcept..text
	$.valueCodeableConcept.text					${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.value.x..text


011 Create Pregnancy Status (Invalid 'DataAbsentReason' AND 'valueCodeableConcept')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-pregnancy-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``DataAbsentReason`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Tags]              DataAbsentReason

	ehr.create new ehr    				  							000_ehr_status.json
	create with DataAbsentReason		  							DataAbsentReason				create-pregnancy-status.json
	observation.validate response - 422 (with error message)	422								obs-6: dataAbsentReason SHALL only be present if Observation.value.x. is not present .dataAbsentReason.empty.. or value.empty...			Observation




012 Create Pregnancy Status (Invalid/Missing 'DataAbsentReason')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-pregnancy-status.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``DataAbsentReason`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create pregnancy status with ehr reference AND data absentreason
    [Tags]              DataAbsentReason

	# FIELD/PATH								VALUE					HTTP	ERROR MESSAGE																								Location
	# 																	CODE

	# missing valueCodeableConcept
#	$.dataAbsentReason							missing					422    	Index 0 out of bounds for length 0
	$.dataAbsentReason							${EMPTY}				422    	This property must be an Object, not a primitive property													Observation.dataAbsentReason

	# wrong format
	$.dataAbsentReason							${{ [] }}				422    	This property must be an Object, not an array																Observation.dataAbsentReason
	$.dataAbsentReason							${{ {} }}				422    	Object must have some content																				Observation.dataAbsentReason
	$.dataAbsentReason							${{ [{}] }}				422    	This property must be an Object, not an array

	# missing coding
#	$.dataAbsentReason.coding					missing					422    	Index 0 out of bounds for length 0
	$.dataAbsentReason.coding					${EMPTY}				422    	This property must be an Array, not a primitive property													Observation.dataAbsentReason.coding

	# invalid system
	$.dataAbsentReason.coding[0].system			${EMPTY}				422    	@value cannot be empty																						Observation.dataAbsentReason.coding.0..system
	$.dataAbsentReason.coding[0].system			${randstring}			422    	Coding.system must be an absolute reference, not a local reference											Observation.dataAbsentReason.coding.0.
	$.dataAbsentReason.coding[0].system			${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.dataAbsentReason.coding.0..system
#	$.dataAbsentReason.coding[0].system			http://foobar.de		422    	Index 0 out of bounds for length 0

	# invalid code
	$.dataAbsentReason.coding[0].code			${EMPTY}				422    	@value cannot be empty																						Observation.dataAbsentReason.coding.0..code
#	$.dataAbsentReason.coding[0].code			${randstring}			422    	Index 0 out of bounds for length 0
	$.dataAbsentReason.coding[0].code			${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.dataAbsentReason.coding.0..code

	# invalid display
	$.dataAbsentReason.coding[0].display		${EMPTY}				422    	@value cannot be empty																						Observation.dataAbsentReason.coding.0..display
	$.dataAbsentReason.coding[0].display		${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.dataAbsentReason.coding.0..display


013 Create Pregnancy Status (invalid multi)
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-pregnancy-status.json_\n\n
	...                 3. *UPDATE* values for attributes \n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status \n\n
    ...                 6. *VALIDATE* outcome against diagnostic text (english + german)

    [Tags]             multi
    [Template]         create pregnancy status JSON
#|  resourceType   |          		ID   			|           meta         						|                              identifier                         |	  status  	|                         				category                           					        					   |                                        									code          								|         subject                    |	  DateTime	   |                      			     valueCodeableConcept			           		           |  R.-Code  |                                                             diagnostics 							                                              |   location
#|                 |                				|  available  | 			profile  			|  available  |        system         |             value         |             |  available  |  codingavailable  |  				system  									   |    	 code          |  available  |  coding available  |  			0.system	  		|  		0.code	  |    		   0.display 		    |  available  |   Identifier-value   |                 |  available  |		coding	|         system     	 |  		code      	|     display      |           |               				                                    ENG				                                                                  |

# all attributes invalid for valueCodeableConcept
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${identifiersystem}     ${identifiervalue}           final		     true             true           http://terminology.hl7.org/CodeSystem/observation-category         social-history          true			   true		  		 http://loinc.org		  		  82810-3         Pregnancy status            		  true    		 	valid      		  2020-02-25		  true		    true	       ${EMPTY}	               ${EMPTY}    	        ${EMPTY}		    422          @value cannot be empty                                                                                                                              Observation.value.ofType.CodeableConcept..coding.0..display
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${identifiersystem}     ${identifiervalue}           final		     true             true           http://terminology.hl7.org/CodeSystem/observation-category         social-history          true			   true		  		 http://loinc.org		  		  82810-3         Pregnancy status           		  true    		 	valid      		  2020-02-25		  true		    true           ${1234}	               test      	        ${1234}		        422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.value.x..coding.0..display
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${identifiersystem}     ${identifiervalue}           final		     true             true           http://terminology.hl7.org/CodeSystem/observation-category         social-history          true			   true		  		 http://loinc.org		  		  82810-3         Pregnancy status            		  true    		 	valid      		  2020-02-25		  true		    true	       ${EMPTY}	             http://google.com      test   		        422          ele-1: All FHIR elements must have a @value or children                                                                                             Observation.value.ofType.CodeableConcept..coding.0..system
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${identifiersystem}     ${identifiervalue}           final		     true             true           http://terminology.hl7.org/CodeSystem/observation-category         social-history          true			   true		  		 http://loinc.org		  		  82810-3         Pregnancy status            		  true    		 	valid      		  2020-02-25		  true		    true	       missing	               ${EMPTY}             missing		        422          @value cannot be empty                                                                                                                              Observation.value.ofType.CodeableConcept..coding.0..code
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${identifiersystem}     ${identifiervalue}           final		     true             true           http://terminology.hl7.org/CodeSystem/observation-category         social-history          true			   true		  		 http://loinc.org		  		  82810-3         Pregnancy status            	 	  true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               missing 	            test   		        422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.value.x..coding.0..system

# all attributes invalid for code
    Observation    	        pregnancy-status  		       true         ${pregnancy_status-url}			  true       ${identifiersystem}     ${identifiervalue}           final		     true             true           http://terminology.hl7.org/CodeSystem/observation-category         social-history          true			   false	  		 http://loinc.org		  		  82810-3         Pregnancy status           		  true    		 	valid      		  2020-02-25		  true		    true	       http://loinc.org	       LA26683-5    	    Not pregnant		422          Observation.code.coding: minimum required = 1, but only found 0 .from ${pregnancy_status-url}.                                                      Observation.code
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${identifiersystem}     ${identifiervalue}           final		     true             true           http://terminology.hl7.org/CodeSystem/observation-category         social-history          true			   true			  	 ${EMPTY}             	  		  ${EMPTY}        ${EMPTY}                            true    		 	valid      		  2020-02-25		  true		    true	       http://loinc.org	       LA26683-5    	    Not pregnant		422          @value cannot be empty                                                                                                                          	 Observation.code.coding.0..display
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${identifiersystem}     ${identifiervalue}           final		     true             true           http://terminology.hl7.org/CodeSystem/observation-category         social-history          true			   true			  	 http://google.com		  		  ${12345}        missing                             true    		 	valid      		  2020-02-25		  true		    true	       http://loinc.org	       LA26683-5    	    Not pregnant		422          The pattern .system http://loinc.org, code 82810-3, and display 'null'. defined in the profile ${pregnancy_status-url}                              Observation.code
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${identifiersystem}     ${identifiervalue}           final		     true             true           http://terminology.hl7.org/CodeSystem/observation-category         social-history          true			   true			  	 test            		  		  missing         ${12345}                            true    		 	valid      		  2020-02-25		  true		    true	       http://loinc.org	       LA26683-5    	    Not pregnant		422          Coding.system must be an absolute reference, not a local reference                                                                                  Observation.code.coding.0.
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${identifiersystem}     ${identifiervalue}           final		     true             true           http://terminology.hl7.org/CodeSystem/observation-category         social-history          true			   true			  	 missing         		  	      test            test1234                            true    		 	valid      		  2020-02-25	      true		    true	       http://loinc.org	       LA26683-5    	    Not pregnant		422          A code with no system has no defined meaning. A system should be provided                                                                           Observation.code.coding.0.

# all attributes invalid for category
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${identifiersystem}     ${identifiervalue}           final		     true             true           missing                                                            ${EMPTY}                true			   true			  	 http://loinc.org		  		  82810-3         Pregnancy status            		  true    		 	valid      		  2020-02-25		  true		    true	       http://loinc.org	       LA26683-5    	    Not pregnant		422          @value cannot be empty                                                                                                                              Observation.category.0..coding.0..code
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${identifiersystem}     ${identifiervalue}           final		     true             true           ${EMPTY}                                                           ${12345}                true			   true			  	 http://loinc.org		  		  82810-3         Pregnancy status            		  true    		 	valid      		  2020-02-25		  true		    true	       http://loinc.org	       LA26683-5    	    Not pregnant		422          @value cannot be empty                                                                                                                              Observation.category.0..coding.0..system
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${identifiersystem}     ${identifiervalue}           final		     true             true           ${12345}                                                           missing                 true			   true			     http://loinc.org		  		  82810-3         Pregnancy status            		  true    		 	valid      		  2020-02-25		  true		    true	       http://loinc.org	       LA26683-5    	    Not pregnant		422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.category.0..coding.0..system
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${identifiersystem}     ${identifiervalue}           final		     true             true           test                                                               ${EMPTY}                true			   true			  	 http://loinc.org		  		  82810-3         Pregnancy status            		  true    		 	valid      		  2020-02-25		  true		    true	       http://loinc.org	       LA26683-5    	    Not pregnant		422          @value cannot be empty                                                                                                                				 Observation.category.0..coding.0..code

# mix invalid attributes
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${randinteger}     	 ${randinteger}             final		     true             true           ${1234}                                                            ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}         ${1234}                           true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.value.x..coding.0..display
    ${1234}      	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${randinteger}     	 ${randinteger}             final		     true             true           ${1234}                                                            ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}         ${1234}                           true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          This does not appear to be a FHIR resource .unknown name '1234'.                                                                                    1234
    Observation    	        ${1234}      				   true         ${pregnancy_status-url}			  true       ${randinteger}     	 ${randinteger}             final		     true             true           ${1234}                                                            ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}         ${1234}                           true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.id
    Observation    	        pregnancy-status  			   false        ${pregnancy_status-url}			  true       ${randinteger}     	 ${randinteger}             final		     true             true           ${1234}                                                            ${1234}                 true			   true			  	 ${1234}          		  	      ${1234}         ${1234}                           true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Coding.system must be an absolute reference, not a local reference                                                                                  Observation.value.ofType.CodeableConcept..coding.0.
    Observation    	        pregnancy-status  			   true         ${1234}                			  true       ${randinteger}     	 ${randinteger}             final		     true             true           ${1234}                                                            ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}         ${1234}                           true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Profile reference '1234' could not be resolved, so has not been checked                                                                             Observation.meta.profile.0.
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  false      ${randinteger}     	 ${randinteger}             final		     true             true           ${1234}                                                            ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}         ${1234}                           true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.category.0..coding.0..system
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${randinteger}     	 ${randinteger}             ${1234}	     	true             true           ${1234}                                                            ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}         ${1234}                           true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.status
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${randinteger}     	 ${randinteger}             final  	     false            true           ${1234}                                                            ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}         ${1234}                           true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.code.coding.0..system
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${randinteger}     	 ${randinteger}             final  	     true             false          ${1234}                                                            ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}         ${1234}                           true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Object must have some content                                                                                                                       Observation.category.0.
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${randinteger}     	 ${randinteger}             final  	     true             true           ${1234}                                                            ${1234}                 false			   true			  	 ${1234}          		  	      ${1234}         ${1234}                           true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Observation.code: minimum required = 1, but only found 0 .from ${pregnancy_status-url}.                                                               Observation
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${randinteger}     	 ${randinteger}             final  	     true             true           ${1234}                                                            ${1234}                 true			   false		     ${1234}          		  		  ${1234}         ${1234}                           true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Expected 1 but found 0 coding elements                                                                                                              Observation.code
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${randinteger}     	 ${randinteger}             final  	     true             true           ${1234}                                                            ${1234}                 true			   true 			 ${1234}          		  		  ${1234}         ${1234}                           false    	 	    valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Observation.subject: minimum required = 1, but only found 0 .from ${pregnancy_status-url}.                                                            Observation
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${randinteger}     	 ${randinteger}             final  	     true             true           ${1234}                                                            ${1234}                 true			   false		     ${1234}          		  		  ${1234}         ${1234}                           true    		 	test      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Expected 1 but found 0 coding elements                                                                                                              Observation.code
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${randinteger}     	 ${randinteger}             final  	     true             true           ${1234}                                                            ${1234}                 true			   false			 ${1234}          		  		  ${1234}         ${1234}                           true    		 	valid      		  ${12345}		      true		    true	       ${1234}	               ${1234}              ${1234}		        422          Not a valid date/time .12345.                                                                                                                       Observation.effective.ofType.dateTime.
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${randinteger}     	 ${randinteger}             final  	     true             true           ${1234}                                                            ${1234}                 true			   false			 ${1234}          		  		  ${1234}         ${1234}                           true    		 	valid      		  2020-02-25		  false		    true	       ${1234}	               ${1234}              ${1234}		        422          Expected 1 but found 0 coding elements                                                                                                              Observation.code
    Observation    	        pregnancy-status  			   true         ${pregnancy_status-url}			  true       ${randinteger}     	 ${randinteger}             final  	     true             true           ${1234}                                                            ${1234}                 true			   false			 ${1234}          		  		  ${1234}         ${1234}                           true    		 	valid      		  2020-02-25		  true		    false	       ${1234}	               ${1234}         	    ${1234}		        422          Expected 1 but found 0 coding elements                                                                                                              Observation.code


#--------------------------------------------------------------------------------------------------------------------------------------------------------------------
# BUG TRACE
#--------------------------------------------------------------------------------------------------------------------------------------------------------------------
BUG TRACE 02 Create Pregnancy Status (Invalid/Missing 'valueCodeableConcept')
	[Documentation]		Belongs to TC 010! Remove separation when it's fixed! -> fixed that it has now 201 as respone and not 500 anymore
	[Template]			create pregnancy status with ehr reference
    [Tags]              category    not-ready    not-ready_bug

	# FIELD/PATH								VALUE					HTTP	ERROR MESSAGE																								Location
	# 																	CODE
	
	# missing valueCodeableConcept
	$.valueCodeableConcept						missing					422    	Index 0 out of bounds for length 0

	# missing coding
	$.valueCodeableConcept.coding 				missing					422    	Index 0 out of bounds for length 0

	# invalid system
	$.valueCodeableConcept.coding[0].system		http://foobar.de		422    	This property must be an Array, not a primitive property													Observation.value.ofType.CodeableConcept..coding.0..system


BUG TRACE 03 Create Pregnancy Status (Invalid/Missing 'DataAbsentReason')
	[Documentation]		Belongs to TC 012! Remove separation when it's fixed!
	[Template]			create pregnancy status with ehr reference AND data absentreason
    [Tags]              DataAbsentReason    not-ready    not-ready_bug

	# FIELD/PATH								VALUE					HTTP	ERROR MESSAGE																								Location
	# 																	CODE

	# missing valueCodeableConcept
	$.dataAbsentReason							missing					422    	Index 0 out of bounds for length 0

	# missing coding
	$.dataAbsentReason.coding					missing					422    	Index 0 out of bounds for length 0

	# invalid system
	$.dataAbsentReason.coding[0].system			http://foobar.de		422    	Index 0 out of bounds for length 0

	# invalid code
	$.dataAbsentReason.coding[0].code			${randstring}			422    	Index 0 out of bounds for length 0
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


create pregnancy status with ehr reference
	[Arguments]         ${json_path}        ${value}                 ${http_status_code}
	...					${error_message}    ${location}=${None}

						ehr.create new ehr                      000_ehr_status.json
	${payload}=    		generate payload from example json      ${json_path}                ${value}
						observation.POST /Observation           Pregnancy Status            ${payload}
						observation.validate response - 422 (with error message)        ${http_status_code}
						...															        ${error_message}
						...															        ${location}


create pregnancy status w/o ehr reference    
	[Arguments]         ${json_path}        ${value}                ${http_status_code}
	...					${error_message}    ${location}=${None}

	${fake_ehr_ref}=	Evaluate    str(uuid.uuid4())    uuid
						Set Test Variable    ${subject_id}    ${fake_ehr_ref}
	${payload}=    		generate payload from example json    ${json_path}    ${value}
						observation.POST /Observation    Pregnancy Status    ${payload}
						observation.validate response - 422 (with error message)      ${http_status_code}
						...															      ${error_message}
						...															      ${location}


generate payload from example json
	[Documentation]		Generates actual request payload using example json as a starting point.
	[Arguments]			${json_path}    ${value}

	${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/create-pregnancy-status.json
                        Update Value To Json    ${payload}    $.subject.identifier.value    ${subject_id}
						Delete Object From Json    ${payload}    $.text

						# comment: delete field/object that has value 'missing' in test case table 
						Run Keyword And Return If   $value=="missing"
						...    	Run Keyword    Delete Object From Json    ${payload}    ${json_path}

						# comment: set value from data table in test case
						Update Value To Json            ${payload}    ${json_path}    ${value}
						Output Debug Info To Console    ${payload}

	[Return]			${payload}


create pregnancy status JSON
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
                        ...    load JSON                        create-pregnancy-status.json				    AND
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
                        ...    observation.validate response - 422 (with error message)						${http_status_code}    			${error_message}    		${location}


generate payload from example json with data absentreason
	[Documentation]		Generates actual request payload using example json as a starting point.
	[Arguments]			${json_path}    ${value}

	${dict_dataabsentreason}			Create Dictionary	dataAbsentReason=${{ {"coding": [{"system": "http://terminology.hl7.org/CodeSystem/data-absent-reason", "code": "unknown", "display": "unknown"}], "text": "pregnancy status"} }}

	${payload}          Load JSON From File    		${DATA_SET_PATH_OBSERVATION}/create-pregnancy-status.json
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


create pregnancy status with ehr reference AND data absentreason
	[Arguments]         ${json_path}        ${value}                 ${http_status_code}
	...					${error_message}    ${location}=${None}

						ehr.create new ehr    000_ehr_status.json
	${payload}=    		generate payload from example json with data absentreason    ${json_path}    ${value}
						observation.POST /Observation    pregnancy status    ${payload}
						observation.validate response - 422 (with error message)     ${http_status_code}
						...															     ${error_message}
						...															     ${location}


create with DataAbsentReason
    [Arguments]         ${fhir_resource_name}    ${example_json}

	${dict_dataabsentreason}			Create Dictionary	dataAbsentReason=${{ {"coding": [{"system": "http://terminology.hl7.org/CodeSystem/data-absent-reason", "code": "unknown", "display": "unknown"}], "text": "pregnancy status"} }}

    ${payload}          Load JSON From File    		${DATA_SET_PATH_OBSERVATION}/${example_json}
                        Update Value To Json    	${payload}    $.subject.identifier.value    				${subject_id}
						Add Object To Json  		${payload}    $												${dict_dataabsentreason}
                        Output Debug Info To Console    ${payload}
                        POST /Observation    		${fhir_resource_name}    ${payload}