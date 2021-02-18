# Copyright (c) 2021 Dave Petzold (Appsfactory GmbH)
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
Force Tags              observation_create    observation_create_invalid    observation_create_invalid_patient-in-icu    create



*** Variables ***
${patient-ICU-url}			    https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/patient-in-icu
${randstring}                   foobar
${randinteger}                  ${12345}
${ID}							cefe30c3-7597-421a-8509-8c15aa2a2d87
${category_URL}					http://terminology.hl7.org/CodeSystem/observation-category
${code_URL}                     https://www.netzwerk-universitaetsmedizin.de/fhir/CodeSystem/ecrf-parameter-codes
${code_display}					Is the patient in the intensive care unit?
${vCC_URL}		                http://snomed.info/sct




*** Test Cases ***

001 Create Patient in ICU (Invalid/Missing 'Subject')
    [Documentation]     1. *LOAD* _create-patient-in-icu.json_ \n\n
	...                 2. *UPDATE* values for attribute ``Subject`` \n\n
    ...                 3. *POST* example JSON to observation endpoint \n\n
	...                 4. *VALIDATE* the response status \n\n
    ...                 5. *VALIDATE* outcome against diagnostic text & location
	[Template]			create patient in ICU w/o ehr reference
	[Tags]              subject

	# FIELD/PATH					VALUE							HTTP	ERROR MESSAGE																	                                            Location
	# 																CODE

	# invalid cases for value
    $.subject.identifier.value		missing							422		 Subject identifier is required
    $.subject.identifier.value		foobar							422		 EhrId not found for subject 'foobar'
    $.subject.identifier.value		${EMPTY}						422		 @value cannot be empty                                        					                                            Observation.subject.identifier.value
    $.subject.identifier.value		${{ [] }}						422		 This property must be an simple value, not an array           					                                            Observation.subject.identifier.value
    $.subject.identifier.value		${{ {} }}						422		 This property must be an simple value, not an object          					                                            Observation.subject.identifier.value
    $.subject.identifier.value		${123}							422		 Error parsing JSON: the primitive value must be a string      					                                            Observation.subject.identifier.value

	# invalid cases for system
    $.subject.identifier.system		foobar							422		Identifier.system must be an absolute reference, not a local reference                                                      Observation.subject.identifier
    $.subject.identifier.system		${EMPTY}						422		@value cannot be empty                                         					                                            Observation.subject.identifier.system
    $.subject.identifier.system		${{ [] }}						422		 This property must be an simple value, not an array           					                                            Observation.subject.identifier.system
    $.subject.identifier.system		${{ {} }}						422		 This property must be an simple value, not an object          					                                            Observation.subject.identifier.system
    $.subject.identifier.system		${123}							422		 Error parsing JSON: the primitive value must be a string      					                                            Observation.subject.identifier.system

	# invalid cases for identifier
    $.subject.identifier			missing							422		 Object must have some content                                 					                                            Observation.subject
    $.subject.identifier			${EMPTY}						422		 This property must be an Object, not a primitive property     					                                            Observation.subject.identifier
    $.subject.identifier			${{ [] }}						422		 This property must be an Object, not an array                 					                                            Observation.subject.identifier
    $.subject.identifier			${{ {} }}						422		 Object must have some content                                 					                                            Observation.subject.identifier
    $.subject.identifier			${123}							422		 This property must be an Object, not a primitive property     					                                            Observation.subject.identifier

	# invalid cases for subject
    $.subject						missing							422		 Observation.subject: minimum required = 1, but only found 0 .from ${patient-ICU-url}
    $.subject						${EMPTY}						422		 This property must be an Object, not a primitive property     					                                            Observation.subject
    $.subject						${{ [] }}						422		 This property must be an Object, not an array                 					                                            Observation.subject
    $.subject						${{ {} }}						422		 Object must have some content                                 					                                            Observation.subject
    $.subject						${123}							422		 This property must be an Object, not a primitive property     					                                            Observation.subject
	
	# comment: random uuid																			 regex for uuid
    $.subject.identifier.value    ${{str(uuid.uuid4())}}    		422     EhrId not found for subject '([0-9a-f]{32}|[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})'
	

002 Create Patient in ICU (Invalid/Missing 'resourceType')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-patient-in-icu.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``resourceType`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]		    create patient in ICU with ehr reference
    [Tags]          	resourceType

	# FIELD/PATH					VALUE							HTTP	ERROR MESSAGE																	            Location
	# 																CODE
    $.resourceType					missing							422		Unable to find resourceType propert                                                         
    $.resourceType					${randstring}					422		This does not appear to be a FHIR resource .unknown name '${randstring}'.
    $.resourceType					${EMPTY}						422		This does not appear to be a FHIR resource .unknown name ''.
    $.resourceType					${randinteger}					422		This does not appear to be a FHIR resource .unknown name '${randinteger}'.


003 Create Patient in ICU (Invalid/Missing 'ID')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-patient-in-icu.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``ID`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]		    create patient in ICU with ehr reference
    [Tags]          	ID

	# FIELD/PATH					VALUE							HTTP	ERROR MESSAGE																	Location
	# 																CODE
    $.id							${EMPTY}						422		@value cannot be empty															Observation.id												
    $.id							${randinteger}					422		Error parsing JSON: the primitive value must be a string						Observation.id
    $.id    						${{ [] }}						422    	This property must be an simple value, not an array                     		Observation.id
    $.id    						${{ {} }}						422    	This property must be an simple value, not an object							Observation.id
    $.id    						${{ [{}] }}						422    	This property must be an simple value, not an array								Observation.id


004 Create Patient in ICU (Invalid/Missing 'meta')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-patient-in-icu.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID: ${subject_id}_ which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``meta`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create patient in ICU with ehr reference
    [Tags]              meta

	# FIELD/PATH					VALUE							HTTP	ERROR MESSAGE																									Location
	# 																CODE

    #invalid meta
    $.meta							missing							422    	Default profile is not supported for Observation. One of the following profiles is expected: .https://.*
    $.meta							${{ {} }}						422    	Object must have some content																	                Observation.meta

    #invalid profil
    $.meta.profile					missing							422    	Object must have some content																					Observation.meta
    $.meta.profile					${EMPTY}						422    	This property must be an Array, not a a primitive property														Observation.meta.profile
    $.meta.profile[0]				${randinteger}					422    	Canonical URLs must be absolute URLs if they are not fragment references .${randinteger}.						Observation.meta.profile.0.
    $.meta.profile[0]				${randstring}					422    	Canonical URLs must be absolute URLs if they are not fragment references .${randstring}.						Observation.meta.profile.0.
    $.meta.profile    				${{ ["invalid_url"] }}		  	422    	Canonical URLs must be absolute URLs if they are not fragment references .invalid_url.							Observation.meta.profile.0.
    $.meta.profile    				${{ ["http://wrong.url"] }}	   	422    	Profile reference 'http://wrong.url' could not be resolved, so has not been checked								Observation.meta.profile.0.
    $.meta.profile[0]				${EMPTY}						422    	@value cannot be empty                                  														Observation.meta.profile.0.
	
	# comment: the next one sets the value to an empty list/array []
    $.meta.profile					${{ [] }}						422    	Default profile is not supported for Observation. One of the following profiles is expected: .https://.*
	
	# comment: the next one sets value to an empty object {}
    $.meta.profile					${{ {} }}						422    	This property must be an Array, not a an object


005 Create Patient in ICU (Invalid/Missing 'Status')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-patient-in-icu.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``Status`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]		    create patient in ICU with ehr reference
    [Tags]          	status

	# FIELD/PATH					VALUE							HTTP	ERROR MESSAGE																								Location
	# 																CODE
    $.status						missing							422		Observation.status: minimum required = 1, but only found 0 .from https://.*									Observation
    $.status						${EMPTY}						422		@value cannot be empty																						Observation.status
    $.status						${randinteger}					422		Error parsing JSON: the primitive value must be a string													Observation.status
    $.status						${randstring}					400		Failed to parse request body as JSON resource. Error was: .element=\"status\". Invalid attribute value \"foobar\": Unknown ObservationStatus code '${randstring}'
    $.status						${{ [] }}						422    	This property must be an simple value, not an array                     									Observation.status
    $.status						${{ {} }}						422    	This property must be an simple value, not an object										                Observation.status
    $.status						${{ [{}] }}						422    	This property must be an simple value, not an array															Observation.status


006 Create Patient in ICU (Invalid/Missing 'category')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-patient-in-icu.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``Category`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create patient in ICU with ehr reference
    [Tags]              category

	# FIELD/PATH							VALUE					HTTP	ERROR MESSAGE																								Location
	# 																CODE

	# invalid category
    $.category								${EMPTY}				422    	This property must be an Array, not a primitive property                            						Observation.category
    $.category								${{ [] }}				422    	Array cannot be empty - the property should not be present if it has no values								Observation.category
	$.category								${{ {} }}				422    	This property must be an Array, not an Object																Observation.category
	$.category								${{ [{}] }}				422    	Object must have some content																				Observation.category

	#invalid coding
	$.category[0].coding    				missing					422    	Object must have some content																				Observation.category.0.
	$.category[0].coding    				${EMPTY}				422    	This property must be an Array, not a primitive property													Observation.category.0..coding
	$.category[0].coding					${{ [] }}				422    	Array cannot be empty - the property should not be present if it has no values								Observation.category.0..coding
	$.category[0].coding					${{ {} }}				422    	This property must be an Array, not an Object																Observation.category.0..coding
	$.category[0].coding					${{ [{}] }}				422    	Object must have some content																				Observation.category.0..coding

	#invalid code 0
	$.category[0].coding[0].code    		${EMPTY}    	    	422    	@value cannot be empty																						Observation.category.0..coding.0..code
#	$.category[0].coding[0].code    		${randstring}	    	422    	This element does not match any known slice defined in the profile ${patient-ICU-url}						Observation.category.0..coding.0.
	$.category[0].coding[0].code    		${randinteger}	    	422    	Error parsing JSON: the primitive value must be a string													Observation.category.0..coding.0..code
    $.category[0].coding[0].code 			${{ [] }}				422    	This property must be an simple value, not an array                                							Observation.category.0..coding.0..code
	$.category[0].coding[0].code 			${{ {} }}				422    	This property must be an simple value, not an object														Observation.category.0..coding.0..code
	$.category[0].coding[0].code 			${{ [{}] }}				422    	This property must be an simple value, not an array															Observation.category.0..coding.0..code

	# invaild system 0
	$.category[0].coding[0].system    		${EMPTY}    	    	422    	@value cannot be empty																						Observation.category.0..coding.0..system
	$.category[0].coding[0].system    		${randstring}	    	422    	Coding.system must be an absolute reference, not a local reference											Observation.category.0..coding.0.
	$.category[0].coding[0].system    		${randinteger}	    	422    	Error parsing JSON: the primitive value must be a string													Observation.category.0..coding.0..system
#	$.category[0].coding[0].system    		http://foobar.de      	422    	This element does not match any known slice defined in the profile ${patient-ICU-url}						Observation.category.0..coding.0.
    $.category[0].coding[0].system 			${{ [] }}				422    	This property must be an simple value, not an array                                							Observation.category.0..coding.0..system
	$.category[0].coding[0].system 			${{ {} }}				422    	This property must be an simple value, not an object														Observation.category.0..coding.0..system
	$.category[0].coding[0].system 			${{ [{}] }}				422    	This property must be an simple value, not an array															Observation.category.0..coding.0..system


007 Create Patient in ICU (Invalid/Missing 'code')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-patient-in-icu.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``Code`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create patient in ICU with ehr reference
    [Tags]              code

	# FIELD/PATH							VALUE					HTTP	ERROR MESSAGE																								Location
	# 																CODE

	# invalid code
	$.code									missing					422    	Observation.code: minimum required = 1, but only found 0 .from ${patient-ICU-url}							Observation
	$.code									${EMPTY}				422    	Observation.code: minimum required = 1, but only found 0 .from ${patient-ICU-url}							Observation
	$.code									${{ [] }}				422    	Observation.code: minimum required = 1, but only found 0 .from ${patient-ICU-url}							Observation
	$.code									${{ {} }}				422    	Object must have some content																				Observation.code
	$.code									${{ [{}] }}				422    	This property must be an Object, not an array																Observation.code

	# invalid coding
	$.code.coding   	 					missing					422    	Observation.code.coding: minimum required = 1, but only found 0												Observation.code
	$.code.coding	    					${EMPTY}				422    	This property must be an Array, not a primitive property													Observation.code.coding
	$.code.coding							${{ [] }}				422    	Observation.code.coding: minimum required = 1, but only found 0 .from ${patient-ICU-url}					Observation.code
	$.code.coding							${{ {} }}				422    	Object must have some content																				Observation.code.coding
	$.code.coding							${{ [{}] }}				422    	This element does not match any known slice defined in the profile ${patient-ICU-url}						Observation.code.coding.0.

	# invalid Code Coding 0 System
	$.code.coding[0].system					missing					422    	A code with no system has no defined meaning. A system should be provided									Observation.code.coding.0.
	$.code.coding[0].system					${EMPTY}				422    	@value cannot be empty																						Observation.code.coding.0..system
	$.code.coding[0].system					http://foobar.de		422    	This element does not match any known slice defined in the profile ${patient-ICU-url}						Observation.code.coding.0.
	$.code.coding[0].system					${randstring}			422    	Coding.system must be an absolute reference, not a local reference											Observation.code.coding.0.
	$.code.coding[0].system					${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.code.coding.0..system
    $.code.coding[0].system      			${{ [] }}				422    	This property must be an simple value, not an array                                							Observation.code.coding.0..system
	$.code.coding[0].system      			${{ {} }}				422    	This property must be an simple value, not an object														Observation.code.coding.0..system
	$.code.coding[0].system      			${{ [{}] }}				422    	This property must be an simple value, not an array															Observation.code.coding.0..system

	# invalid Code Coding 0 Code
	$.code.coding[0].code					missing					422    	This element does not match any known slice defined in the profile ${patient-ICU-url}						Observation.code.coding.0.
	$.code.coding[0].code					${EMPTY}				422    	@value cannot be empty																						Observation.code.coding.0..code
	$.code.coding[0].code					${randstring}			422    	This element does not match any known slice defined in the profile ${patient-ICU-url}						Observation.code.coding.0.
	$.code.coding[0].code					${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.code.coding.0..code
    $.code.coding[0].code      			    ${{ [] }}				422    	This property must be an simple value, not an array                                							Observation.code.coding.0..code
	$.code.coding[0].code      			    ${{ {} }}				422    	This property must be an simple value, not an object														Observation.code.coding.0..code
	$.code.coding[0].code      			    ${{ [{}] }}				422    	This property must be an simple value, not an array															Observation.code.coding.0..code

	# invalid Code Coding 0 Display
	$.code.coding[0].display				${EMPTY}				422    	@value cannot be empty																						Observation.code.coding.0..display
	$.code.coding[0].display				${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.code.coding.0..display
    $.code.coding[0].display    			${{ [] }}				422    	This property must be an simple value, not an array                                							Observation.code.coding.0..display
	$.code.coding[0].display    			${{ {} }}				422    	This property must be an simple value, not an object														Observation.code.coding.0..display
	$.code.coding[0].display    			${{ [{}] }}				422    	This property must be an simple value, not an array															Observation.code.coding.0..display


008 Create Patient in ICU (Invalid/Missing 'effectiveDateTime')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-patient-in-icu.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``effectiveDateTime`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create patient in ICU with ehr reference
    [Tags]              effectiveDateTime

	# FIELD/PATH							VALUE					HTTP	ERROR MESSAGE																								Location
	# 																CODE
	
	# missing attribute
	$.effectiveDateTime						missing					422    	Observation.effective.x.: minimum required = 1, but only found 0 .from ${patient-ICU-url}.					Observation
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


009 Create Patient in ICU (Invalid/Missing 'valueCodeableConcept')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-patient-in-icu.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``valueCodeableConcept`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create patient in ICU with ehr reference
    [Tags]              valueCodeableConcept

	# FIELD/PATH								VALUE					HTTP	ERROR MESSAGE																								    Location
	# 																	CODE
	
	# missing valueCodeableConcept
	$.valueCodeableConcept						${EMPTY}				422    	This property must be an Object, not a primitive property												    	Observation.value.x.

	# wrong format
	$.valueCodeableConcept						${{ [] }}				422    	This property must be an Object, not an array													    			Observation.value.x.
	$.valueCodeableConcept						${{ {} }}				422    	Object must have some content																		    		Observation.value.x.
	$.valueCodeableConcept						${{ [{}] }}				422    	This property must be an Object, not an array															    	Observation.value.x.

	# missing coding
	$.valueCodeableConcept.coding 				missing					422    	Object must have some content																			    	Observation.value.x.
	$.valueCodeableConcept.coding				${EMPTY}				422    	This property must be an Array, not a primitive property												    	Observation.value.x..coding
	$.valueCodeableConcept.coding				${{ [] }}				422    	Array cannot be empty - the property should not be present if it has no values								    Observation.value.x..coding
	$.valueCodeableConcept.coding				${{ {} }}				422    	This property must be an Array, not an Object																    Observation.value.x..coding
	$.valueCodeableConcept.coding				${{ [{}] }}				422    	Object must have some content																				    Observation.value.x..coding.0.

	# invalid system
	$.valueCodeableConcept.coding[0].system		missing					422    	A code with no system has no defined meaning. A system should be provided   								    Observation.value.ofType.CodeableConcept..coding.0.
	$.valueCodeableConcept.coding[0].system		${EMPTY}				422    	@value cannot be empty																						    Observation.value.ofType.CodeableConcept..coding.0..system
	$.valueCodeableConcept.coding[0].system		http://foobar.de		422    	This element does not match any known slice defined in the profile ${patient-ICU-url}						    Observation.value.ofType.CodeableConcept..coding.0.
	$.valueCodeableConcept.coding[0].system		${randstring}			422    	Coding.system must be an absolute reference, not a local reference											    Observation.value.ofType.CodeableConcept..coding.0.
	$.valueCodeableConcept.coding[0].system		${randinteger}			422    	Error parsing JSON: the primitive value must be a string													    Observation.value.x..coding.0..system
    $.valueCodeableConcept.coding[0].system		${{ [] }}				422    	This property must be an simple value, not an array                         								    Observation.value.x..coding.0..system
	$.valueCodeableConcept.coding[0].system		${{ {} }}				422    	This property must be an simple value, not an object														    Observation.value.x..coding.0..system
	$.valueCodeableConcept.coding[0].system		${{ [{}] }}				422    	This property must be an simple value, not an array 														    Observation.value.x..coding.0..system

	# invalid code
	$.valueCodeableConcept.coding[0].code		missing					422    	Observation.value.x..coding:snomed.code: minimum required = 1, but only found 0 .from ${patient-ICU-url}.		Observation.value.ofType.CodeableConcept..coding.0.
	$.valueCodeableConcept.coding[0].code		${EMPTY}				422    	@value cannot be empty																						    Observation.value.ofType.CodeableConcept..coding.0..code
	$.valueCodeableConcept.coding[0].code		${randstring}			422    	Aktivität durchgeführt has invalid code ${randstring}
	$.valueCodeableConcept.coding[0].code		${randinteger}			422    	Error parsing JSON: the primitive value must be a string													    Observation.value.x..coding.0..code
    $.valueCodeableConcept.coding[0].code		${{ [] }}				422    	This property must be an simple value, not an array                         								    Observation.value.x..coding.0..code
	$.valueCodeableConcept.coding[0].code		${{ {} }}				422    	This property must be an simple value, not an object														    Observation.value.x..coding.0..code
	$.valueCodeableConcept.coding[0].code		${{ [{}] }}				422    	This property must be an simple value, not an array 														    Observation.value.x..coding.0..code

	# invalid display
	$.valueCodeableConcept.coding[0].display	${EMPTY}				422    	@value cannot be empty																						    Observation.value.ofType.CodeableConcept..coding.0..display
	$.valueCodeableConcept.coding[0].display	${randinteger}			422    	Error parsing JSON: the primitive value must be a string													    Observation.value.x..coding.0..display
    $.valueCodeableConcept.coding[0].display	${{ [] }}				422    	This property must be an simple value, not an array                         								    Observation.value.x..coding.0..display
	$.valueCodeableConcept.coding[0].display	${{ {} }}				422    	This property must be an simple value, not an object														    Observation.value.x..coding.0..display
	$.valueCodeableConcept.coding[0].display	${{ [{}] }}				422    	This property must be an simple value, not an array 														    Observation.value.x..coding.0..display


010 Create Patient in ICU (Invalid 'DataAbsentReason' AND 'valueCodeableConcept')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-patient-in-icu.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``DataAbsentReason`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Tags]              DataAbsentReason

	ehr.create new ehr    				  							000_ehr_status.json
	create with DataAbsentReason		  							DataAbsentReason				create-patient-in-icu.json
	observation.validate response - 422 (with error message)	422								obs-6: dataAbsentReason SHALL only be present if Observation.value.x. is not present .dataAbsentReason.empty.. or value.empty...			Observation




011 Create Patient in ICU (Invalid/Missing 'DataAbsentReason')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-patient-in-icu.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``DataAbsentReason`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create patient in ICU with ehr reference AND data absentreason
    [Tags]              DataAbsentReason

	# FIELD/PATH								VALUE					HTTP	ERROR MESSAGE																								Location
	# 																	CODE

	# missing valueCodeableConcept
#	$.dataAbsentReason							missing					422    	Index 0 out of bounds for length 0
	$.dataAbsentReason							${EMPTY}				422    	This property must be an Object, not a primitive property													Observation.dataAbsentReason

	# wrong format valueCodeableConcept
	$.dataAbsentReason							${{ [] }}				422    	This property must be an Object, not an array																Observation.dataAbsentReason
	$.dataAbsentReason							${{ {} }}				422    	Object must have some content																				Observation.dataAbsentReason
	$.dataAbsentReason							${{ [{}] }}				422    	This property must be an Object, not an array                                                               Observation.dataAbsentReason

	# missing coding
	$.dataAbsentReason.coding					${EMPTY}				422    	This property must be an Array, not a primitive property													Observation.dataAbsentReason.coding

	# wrong format coding
	$.dataAbsentReason.coding					${{ [] }}				422    	Array cannot be empty - the property should not be present if it has no values								Observation.dataAbsentReason.coding
	$.dataAbsentReason.coding					${{ {} }}				422    	This property must be an Array, not an Object																Observation.dataAbsentReason.coding
	$.dataAbsentReason.coding					${{ [{}] }}				422    	Object must have some content																				Observation.dataAbsentReason.coding.0.

	# invalid system
	$.dataAbsentReason.coding[0].system			${EMPTY}				422    	@value cannot be empty																						Observation.dataAbsentReason.coding.0..system
	$.dataAbsentReason.coding[0].system			${randstring}			422    	Coding.system must be an absolute reference, not a local reference											Observation.dataAbsentReason.coding.0.
	$.dataAbsentReason.coding[0].system			${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.dataAbsentReason.coding.0..system
#	$.dataAbsentReason.coding[0].system			http://foobar.de		422    	Index 0 out of bounds for length 0
    $.dataAbsentReason.coding[0].system 		${{ [] }}				422    	This property must be an simple value, not an array                         								Observation.dataAbsentReason.coding.0..system
	$.dataAbsentReason.coding[0].system 		${{ {} }}				422    	This property must be an simple value, not an object														Observation.dataAbsentReason.coding.0..system
	$.dataAbsentReason.coding[0].system 		${{ [{}] }}				422    	This property must be an simple value, not an array 														Observation.dataAbsentReason.coding.0..system

	# invalid code
	$.dataAbsentReason.coding[0].code			${EMPTY}				422    	@value cannot be empty																						Observation.dataAbsentReason.coding.0..code
#	$.dataAbsentReason.coding[0].code			${randstring}			422    	Index 0 out of bounds for length 0
	$.dataAbsentReason.coding[0].code			${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.dataAbsentReason.coding.0..code
    $.dataAbsentReason.coding[0].code   		${{ [] }}				422    	This property must be an simple value, not an array                         								Observation.dataAbsentReason.coding.0..code
	$.dataAbsentReason.coding[0].code   		${{ {} }}				422    	This property must be an simple value, not an object														Observation.dataAbsentReason.coding.0..code
	$.dataAbsentReason.coding[0].code   		${{ [{}] }}				422    	This property must be an simple value, not an array 														Observation.dataAbsentReason.coding.0..code

	# invalid display
	$.dataAbsentReason.coding[0].display		${EMPTY}				422    	@value cannot be empty																						Observation.dataAbsentReason.coding.0..display
	$.dataAbsentReason.coding[0].display		${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.dataAbsentReason.coding.0..display
    $.dataAbsentReason.coding[0].display		${{ [] }}				422    	This property must be an simple value, not an array                         								Observation.dataAbsentReason.coding.0..display
	$.dataAbsentReason.coding[0].display		${{ {} }}				422    	This property must be an simple value, not an object														Observation.dataAbsentReason.coding.0..display
	$.dataAbsentReason.coding[0].display		${{ [{}] }}				422    	This property must be an simple value, not an array 														Observation.dataAbsentReason.coding.0..display


012 Create Patient in ICU (invalid multi)
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-patient-in-icu.json_\n\n
	...                 3. *UPDATE* values for attributes \n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status \n\n
    ...                 6. *VALIDATE* outcome against diagnostic text (english + german)
    [Template]         create patient in ICU JSON
    [Tags]             multi

#|  resourceType   |          		ID   			|                      meta        				|   status    |                         				category                           |                                        									code          								|         subject                    |	  DateTime	   |                      			     valueCodeableConcept			           		           			|  R.-Code  |                                                             diagnostics 							                                              |   location
#|                 |                				|  available  | 			profile  			|             |  available  |  codingavailable  |  	system  		|    	 code          |  available  |  coding available  |  			0.system	  		|  		0.code	  |    		   0.display 		    |  available  |   Identifier-value   |                 |  available  |		coding	|         system     	 |  		code      	|     display      			|           |               				                                    ENG				                                                                  |

# all attributes invalid for valueCodeableConcept
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final		     true             true           ${category_URL}         survey			         true			   true		  		 ${code_URL}			  		  01		       ${code_display}            		  true    		 	valid      		  2020-02-25		  true		    true	       ${EMPTY}	               ${EMPTY}    	        ${EMPTY}		    		422          @value cannot be empty                                                                                                                              Observation.value.ofType.CodeableConcept..coding.0..display
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final		     true             true           ${category_URL}         survey			         true			   true		  		 ${code_URL}			  		  01		       ${code_display}            		  true    		 	valid      		  2020-02-25		  true		    true           ${1234}	               test      	        ${1234}		        		422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.value.x..coding.0..display    
	Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final		     true             true           ${category_URL}         survey			         true			   true		  		 ${code_URL}			  		  01		       ${code_display}            		  true    		 	valid      		  2020-02-25		  true		    true	       ${EMPTY}	             http://google.com      test   		        		422          ele-1: All FHIR elements must have a @value or children                                                                                             Observation.value.ofType.CodeableConcept..coding.0..system
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final		     true             true           ${category_URL}         survey			         true			   true		  		 ${code_URL}			  		  01		       ${code_display}            		  true    		 	valid      		  2020-02-25		  true		    true	       missing	               ${EMPTY}             missing		        		422          @value cannot be empty                                                                                                                              Observation.value.ofType.CodeableConcept..coding.0..code
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final		     true             true           ${category_URL}         survey			         true			   true		  		 ${code_URL}			  		  01		       ${code_display}            		  true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               missing 	            test   		        		422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.value.x..coding.0..system

# all attributes invalid for code
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final		     true             true           ${category_URL}         survey			         true			   false	  		 ${code_URL}			  		  01		       ${code_display}            		  true    		 	valid      		  2020-02-25		  true		    true	       ${vCC_URL}	       	   373067005	  	    No (qualifier value)		422          Observation.code.coding: minimum required = 1, but only found 0 .from ${patient-ICU-url}.                                                      	 Observation.code
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final		     true             true           ${category_URL}         survey			         true			   true			  	 ${EMPTY}             	  		  ${EMPTY}     	   ${EMPTY}                           true    		 	valid      		  2020-02-25		  true		    true	       ${vCC_URL}	       	   373067005	  	    No (qualifier value)		422          @value cannot be empty                                                                                                                          	 Observation.code.coding.0..display
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final		     true             true           ${category_URL}         survey			         true			   true			  	 http://google.com		  		  ${12345}   	   missing                            true    		 	valid      		  2020-02-25		  true		    true	       ${vCC_URL}	       	   373067005	   	    No (qualifier value)		422          Observation.code.coding:icu: minimum required = 1, but only found 0 .from ${patient-ICU-url}.                              						 Observation.code
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final		     true             true           ${category_URL}         survey			         true			   true			  	 test            		  		  missing    	   ${12345}                           true    		 	valid      		  2020-02-25		  true		    true	       ${vCC_URL}	       	   373067005	  	    No (qualifier value)		422          Coding.system must be an absolute reference, not a local reference                                                                                  Observation.code.coding.0.
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final		     true             true           ${category_URL}         survey			         true			   true			  	 missing         		  	      test             test1234                           true    		 	valid      		  2020-02-25	      true		    true	       ${vCC_URL}	       	   373067005	  	    No (qualifier value)		422          A code with no system has no defined meaning. A system should be provided                                                                           Observation.code.coding.0.

# all attributes invalid for category
	Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final		     true             true           http://google.com       ${EMPTY}                true			   true		  		 ${code_URL}			  		  01		       ${code_display}            		  true    		 	valid      		  2020-02-25		  true		    true	       ${vCC_URL}	       	   373067005	   	    No (qualifier value)		422     	 @value cannot be empty 								 						 																	 Observation.category.0..coding.0..code
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final		     true             true           missing                 ${12345}                true			   true			  	 ${code_URL}			  		  01		       ${code_display}            		  true    		 	valid      		  2020-02-25		  true		    true	       ${vCC_URL}	       	   373067005	   	    No (qualifier value)		422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.category.0..coding.0..code
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final		     true             true           ${EMPTY}                test	                 true			   true			  	 ${code_URL}			  		  01		       ${code_display}            		  true    		 	valid      		  2020-02-25		  true		    true	       ${vCC_URL}	       	   373067005	   	    No (qualifier value)		422          @value cannot be empty                                                                                                                              Observation.category.0..coding.0..system
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}	    	  final		     true             true           ${12345}                missing                 true			   true			     ${code_URL}			  		  01		       ${code_display}            		  true    		 	valid      		  2020-02-25		  true		    true	       ${vCC_URL}	       	   373067005	   	    No (qualifier value)		422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.category.0..coding.0..system
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}			  final		     true             true           test                    ${EMPTY}                true			   true			  	 ${code_URL}			  		  01		       ${code_display}            		  true    		 	valid      		  2020-02-25		  true		    true	       ${vCC_URL}	       	   373067005	   	    No (qualifier value)		422          @value cannot be empty                                                                                                                				 Observation.category.0..coding.0..code

# mix invalid attributes
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}			  final		     true             true           ${1234}                 ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}         ${1234}                             true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.value.x..coding.0..display
    ${1234}      	        ${ID}		 	  			   true         ${patient-ICU-url}			  final		     true             true           ${1234}                 ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}         ${1234}                             true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          This does not appear to be a FHIR resource .unknown name '1234'.                                                                                    1234
    Observation    	        ${1234}      				   true         ${patient-ICU-url}			  final		     true             true           ${1234}                 ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}         ${1234}                             true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.id
    Observation    	        ${ID}		 	  			   false        ${patient-ICU-url}			  final		     true             true           ${1234}                 ${1234}                 true			   true			  	 ${1234}          		  	      ${1234}         ${1234}                             true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Coding.system must be an absolute reference, not a local reference                                                                                  Observation.value.ofType.CodeableConcept..coding.0.
    Observation    	        ${ID}		 	  			   true         ${1234}                		  final		     true             true           ${1234}                 ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}         ${1234}                             true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Profile reference '1234' could not be resolved, so has not been checked                                                                             Observation.meta.profile.0.
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      ${1234}	     true             true           ${1234}                 ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}         ${1234}                             true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.status
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final  	     false            true           ${1234}                 ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}         ${1234}                             true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.code.coding.0..system
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final  	     true             false          ${1234}                 ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}         ${1234}                             true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Object must have some content                                                                                                                       Observation.category.0.
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final  	     true             true           ${1234}                 ${1234}                 false			   true			  	 ${1234}          		  	      ${1234}         ${1234}                             true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Observation.code: minimum required = 1, but only found 0 .from ${patient-ICU-url}.                                                                Observation
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final  	     true             true           ${1234}                 ${1234}                 true			   false		     ${1234}          		  		  ${1234}         ${1234}                             true    		 	valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Observation.code.coding: minimum required = 1, but only found 0 .from ${patient-ICU-url}.                                                         Observation.code
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}	    	  final  	     true             true           ${1234}                 ${1234}                 true			   true 			 ${1234}          		  		  ${1234}         ${1234}                             false    	 	    valid      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Observation.subject: minimum required = 1, but only found 0 .from ${patient-ICU-url}.                                                             Observation
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final  	     true             true           ${1234}                 ${1234}                 true			   false		     ${1234}          		  		  ${1234}         ${1234}                             true    		 	test      		  2020-02-25		  true		    true	       ${1234}	               ${1234}              ${1234}		        422          Observation.code.coding: minimum required = 1, but only found 0 .from ${patient-ICU-url}.                                                         Observation.code
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final  	     true             true           ${1234}                 ${1234}                 true			   false			 ${1234}          		  		  ${1234}         ${1234}                             true    		 	valid      		  ${12345}		      true		    true	       ${1234}	               ${1234}              ${1234}		        422          Not a valid date/time .12345.                                                                                                                       Observation.effective.ofType.dateTime.
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final  	     true             true           ${1234}                 ${1234}                 true			   false			 ${1234}          		  		  ${1234}         ${1234}                             true    		 	valid      		  2020-02-25		  false		    true	       ${1234}	               ${1234}              ${1234}		        422          Observation.code.coding: minimum required = 1, but only found 0 .from ${patient-ICU-url}. 			                                             Observation.code
    Observation    	        ${ID}		 	  			   true         ${patient-ICU-url}		      final  	     true             true           ${1234}                 ${1234}                 true			   false			 ${1234}          		  		  ${1234}         ${1234}                             true    		 	valid      		  2020-02-25		  true		    false	       ${1234}	               ${1234}         	    ${1234}		        422          Observation.code.coding: minimum required = 1, but only found 0 .from ${patient-ICU-url}. 			                                             Observation.code

#--------------------------------------------------------------------------------------------------------------------------------------------------------------------
# BUG TRACE
#--------------------------------------------------------------------------------------------------------------------------------------------------------------------
BUG TRACE 02 Create Patient in ICU (Invalid/Missing 'category')
	[Documentation]		Belongs to TC 006! Remove separation when it's fixed!
	[Template]			create patient in ICU with ehr reference
    [Tags]              category    not-ready    not-ready_bug

	# FIELD/PATH							VALUE					HTTP	ERROR MESSAGE																								Location
	# 																CODE

	#invalid code 0
	$.category[0].coding[0].code    		${randstring}	    	422    	This element does not match any known slice defined in the profile ${patient-ICU-url}						Observation.category.0..coding.0.

	# invaild system 0
	$.category[0].coding[0].system    		http://foobar.de      	422    	This element does not match any known slice defined in the profile ${patient-ICU-url}						Observation.category.0..coding.0.


BUG TRACE 03 Create Patient in ICU (Invalid/Missing 'valueCodeableConcept')
	[Documentation]		Belongs to TC 009! Remove separation when it's fixed!
	[Template]			create patient in ICU with ehr reference
    [Tags]              valueCodeableConcept    not-ready    not-ready_bug

	# FIELD/PATH								VALUE					HTTP	ERROR MESSAGE																								    Location
	# 																	CODE
	$.valueCodeableConcept						missing					422    	Index 0 out of bounds for length 0


BUG TRACE 04 Create Patient in ICU (Invalid/Missing 'DataAbsentReason')
	[Documentation]		Belongs to TC 011! Remove separation when it's fixed!
	[Template]			create patient in ICU with ehr reference AND data absentreason
    [Tags]              DataAbsentReason    not-ready    not-ready_bug

	# FIELD/PATH								VALUE					HTTP	ERROR MESSAGE																								Location
	# 																	CODE

	# missing valueCodeableConcept
	$.dataAbsentReason							missing					422    	Index 0 out of bounds for length 0

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


create patient in ICU with ehr reference
	[Arguments]         ${json_path}        ${value}                 ${http_status_code}
	...					${error_message}    ${location}=${None}

						ehr.create new ehr                      000_ehr_status.json
	${payload}=    		generate payload from example json      ${json_path}                ${value}
						observation.POST /Observation           Patient in ICU            ${payload}
						observation.validate response - 422 (with error message)        ${http_status_code}
						...															        ${error_message}
						...															        ${location}


create patient in ICU w/o ehr reference    
	[Arguments]         ${json_path}        ${value}                ${http_status_code}
	...					${error_message}    ${location}=${None}

	${fake_ehr_ref}=	Evaluate    str(uuid.uuid4())    uuid
						Set Test Variable    ${subject_id}    ${fake_ehr_ref}
	${payload}=    		generate payload from example json    ${json_path}    ${value}
						observation.POST /Observation    Patient in ICU    ${payload}
						observation.validate response - 422 (with error message)      ${http_status_code}
						...															      ${error_message}
						...															      ${location}


generate payload from example json
	[Documentation]		Generates actual request payload using example json as a starting point.
	[Arguments]			${json_path}    ${value}

	${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/create-patient-in-icu.json
                        Update Value To Json    ${payload}    $.subject.identifier.value    ${subject_id}
						Delete Object From Json    ${payload}    $.text

						# comment: delete field/object that has value 'missing' in test case table 
						Run Keyword And Return If   $value=="missing"
						...    	Run Keyword    Delete Object From Json    ${payload}    ${json_path}

						# comment: set value from data table in test case
						Update Value To Json            ${payload}    ${json_path}    ${value}
						Output Debug Info To Console    ${payload}

	[Return]			${payload}


create patient in ICU JSON
    [Arguments]         ${resourceType}    				${ID}    						${meta}    							${profile}
	...					${status}                       ${categoryavailable}    	    ${categorycodingavailable}    	    ${categorysystem}
    ...                 ${categorycode}                 ${codeavailable}    			${codecodingavailable}    		    ${code0system}
    ...                 ${code0code}                    ${code0display}					${subject}    					    ${subjectvalue}
    ...                 ${effectivedatetime}            ${vCCavailabe}					${vCCCodingavailable}			    ${vCC0System}
    ...                 ${vCC0Code}                     ${vCC0Display}					${http_status_code}    			    ${error_message}
    ...                 ${location}

                        prepare new request session    Prefer=return=representation

    &{resp}             Run Keywords
                        ...    ehr.create new ehr               000_ehr_status.json                             AND
                        ...    load JSON                        create-patient-in-icu.json				    AND
                        ...    update Resource Type             ${resourceType}                                 AND
                        ...    update ID                        ${ID}                                           AND
                        ...    update Meta Profile              ${meta}                                         ${profile}                    	AND
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

	${dict_dataabsentreason}			Create Dictionary	dataAbsentReason=${{ {"coding": [{"system": "http://terminology.hl7.org/CodeSystem/data-absent-reason", "code": "unknown", "display": "unknown"}], "text": "Patient in ICU"} }}

	${payload}          Load JSON From File    		${DATA_SET_PATH_OBSERVATION}/create-patient-in-icu.json
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


create patient in ICU with ehr reference AND data absentreason
	[Arguments]         ${json_path}        ${value}                 ${http_status_code}
	...					${error_message}    ${location}=${None}

						ehr.create new ehr    000_ehr_status.json
	${payload}=    		generate payload from example json with data absentreason    ${json_path}    ${value}
						observation.POST /Observation    Patient in ICU    ${payload}
						observation.validate response - 422 (with error message)     ${http_status_code}
						...															     ${error_message}
						...															     ${location}


create with DataAbsentReason
    [Arguments]         ${fhir_resource_name}    ${example_json}

	${dict_dataabsentreason}			Create Dictionary	dataAbsentReason=${{ {"coding": [{"system": "http://terminology.hl7.org/CodeSystem/data-absent-reason", "code": "unknown", "display": "unknown"}], "text": "Patient in ICU"} }}

    ${payload}          Load JSON From File    		${DATA_SET_PATH_OBSERVATION}/${example_json}
                        Update Value To Json    	${payload}    $.subject.identifier.value    				${subject_id}
						Add Object To Json  		${payload}    $												${dict_dataabsentreason}
                        Output Debug Info To Console    ${payload}
                        POST /Observation    		${fhir_resource_name}    ${payload}