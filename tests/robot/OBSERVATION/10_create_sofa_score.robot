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
Force Tags              observation_create    sofa-score    invalid    create



*** Variables ***
${sofa_score-url}			    https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/sofa-score
${sofa_score-url2}				https://www.netzwerk-universitaetsmedizin.de/fhir/CodeSystem/sofa-score
${category-url}					http://terminology.hl7.org/CodeSystem/observation-category
${code-url}						https://www.netzwerk-universitaetsmedizin.de/fhir/CodeSystem/ecrf-parameter-codes
${randstring}                   foobar
${randinteger}                  ${12345}




*** Test Cases ***

001 Create Sofa Score (Invalid/Missing 'Subject')
    [Documentation]     1. *LOAD* _create-sofa-score.json_ \n\n
	...                 2. *UPDATE* values for attribute ``Subject`` \n\n
    ...                 3. *POST* example JSON to observation endpoint \n\n
	...                 4. *VALIDATE* the response status \n\n
    ...                 5. *VALIDATE* outcome against diagnostic text & location
	[Template]			create sofa score w/o ehr reference
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
    $.subject						missing							422		 Observation.subject: minimum required = 1, but only found 0 .from ${sofa_score-url}
    $.subject						${EMPTY}						422		 This property must be an Object, not a primitive property     					Observation.subject
    $.subject						${{ [] }}						422		 This property must be an Object, not an array                 					Observation.subject
    $.subject						${{ {} }}						422		 Object must have some content                                 					Observation.subject
    $.subject						${123}							422		 This property must be an Object, not a primitive property     					Observation.subject
	
	# comment: random uuid																			 regex for uuid
    $.subject.identifier.value    ${{str(uuid.uuid4())}}    		422     EhrId not found for subject
	

002 Create Sofa Score (Invalid/Missing 'resourceType')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``resourceType`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]		    create sofa score with ehr reference
    [Tags]          	resourceType

	# FIELD/PATH					VALUE							HTTP	ERROR MESSAGE																	Location
	# 																CODE
    $.resourceType					missing							422		Unable to find resourceType propert
    $.resourceType					${randstring}					422		This does not appear to be a FHIR resource .unknown name '${randstring}'.
    $.resourceType					${EMPTY}						422		This does not appear to be a FHIR resource .unknown name ''.
    $.resourceType					${randinteger}					422		This does not appear to be a FHIR resource .unknown name '${randinteger}'.


003 Create Sofa Score (Invalid/Missing 'ID')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``ID`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]		    create sofa score with ehr reference
    [Tags]          	ID

	# FIELD/PATH					VALUE							HTTP	ERROR MESSAGE																	Location
	# 																CODE
    $.id							${EMPTY}						422		@value cannot be empty															Observation.id												
    $.id							${randinteger}					422		Error parsing JSON: the primitive value must be a string						Observation.id


004 Create Sofa Score (Invalid/Missing 'meta')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID: ${subject_id}_ which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``Meta`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create sofa score with ehr reference
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


005 Create Sofa Score (Invalid/Missing 'Status')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``Status`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]		    create sofa score with ehr reference
    [Tags]          	status

	# FIELD/PATH					VALUE							HTTP	ERROR MESSAGE																								Location
	# 																CODE
	$.status						missing							422		Observation.status: minimum required = 1, but only found 0 .from https://.*									Observation
	$.status						${EMPTY}						422		@value cannot be empty																						Observation.status
	$.status						${randinteger}					422		Error parsing JSON: the primitive value must be a string													Observation.status
	$.status						${randstring}					400		Failed to parse request body as JSON resource. Error was: .element=\"status\". Invalid attribute value \"foobar\": Unknown ObservationStatus code '${randstring}'


006 Create Sofa Score (Invalid/Missing 'category')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``Category`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create sofa score with ehr reference
    [Tags]              category

	# FIELD/PATH							VALUE					HTTP	ERROR MESSAGE																								Location
	# 																CODE

	# invalid category
	$.category								missing					422    	Observation.category: minimum required = 1, but only found 0 .from ${sofa_score-url}
	$.category								${{ [] }}				422    	Array cannot be empty - the property should not be present if it has no values								Observation.category
	$.category								${{ {} }}				422    	This property must be an Array, not an Object																Observation.category
	$.category								${{ [{}] }}				422    	Object must have some content																				Observation.category

	#invalid coding
	$.category[0].coding    				missing					422    	Object must have some content																				Observation.category.0.
	$.category[0].coding    				${EMPTY}				422    	This property must be an Array, not a primitive property													Observation.category.0..coding
	
	#invalid code 0
	$.category[0].coding[0].code    		missing    		    	422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        Observation.category.0..coding.0.
	$.category[0].coding[0].code    		${EMPTY}    	    	422    	@value cannot be empty																						Observation.category.0..coding.0..code
	$.category[0].coding[0].code    		${randstring}	    	422    	This element does not match any known slice defined in the profile ${sofa_score-url}					    Observation.category.0.
	$.category[0].coding[0].code    		${randinteger}	    	422    	Error parsing JSON: the primitive value must be a string													Observation.category.0..coding.0..code
	
	# invaild system 0
	$.category[0].coding[0].system    		missing    		    	422    	A code with no system has no defined meaning. A system should be provided									Observation.category.0..coding.0.
	$.category[0].coding[0].system    		${EMPTY}    	    	422    	@value cannot be empty																						Observation.category.0..coding.0..system
	$.category[0].coding[0].system    		${randstring}	    	422    	Coding.system must be an absolute reference, not a local reference											Observation.category.0..coding.0.
	$.category[0].coding[0].system    		${randinteger}	    	422    	Error parsing JSON: the primitive value must be a string													Observation.category.0..coding.0..system
	$.category[0].coding[0].system    		http://foobar.de      	422    	This element does not match any known slice defined in the profile ${sofa_score-url}					    Observation.category.0..coding.0.


007 Create Sofa Score (Invalid/Missing 'code')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``Code`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create sofa score with ehr reference
    [Tags]              code

	# FIELD/PATH							VALUE					HTTP	ERROR MESSAGE																								Location
	# 																CODE

	# invalid code
	$.code									missing					422    	Observation.code: minimum required = 1, but only found 0 .from ${sofa_score-url}						    Observation
	$.code									${{ [] }}				422    	Observation.code: minimum required = 1, but only found 0 .from ${sofa_score-url}						    Observation
	$.code									${{ {} }}				422    	Object must have some content																				Observation.code
	$.code									${{ [{}] }}				422    	This property must be an Object, not an array																Observation.code

	# invalid coding
	$.code.coding   	 					missing					422    	Observation.code.coding: minimum required = 1, but only found 0												Observation.code
	$.code.coding	    					${EMPTY}				422    	This property must be an Array, not a primitive property													Observation.code.coding

	# invalid Code Coding 0 System
    $.code.coding[0].system					missing					422    	A code with no system has no defined meaning. A system should be provided									Observation.code.coding.0.
	$.code.coding[0].system					${EMPTY}				422    	@value cannot be empty																						Observation.code.coding.0..system
	$.code.coding[0].system					http://foobar.de		422    	This element does not match any known slice defined in the profile ${sofa_score-url}                    	Observation.code
	$.code.coding[0].system					${randstring}			422    	Coding.system must be an absolute reference, not a local reference											Observation.code.coding.0.
	$.code.coding[0].system					${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.code.coding.0..system

	# invalid Code Coding 0 Code
    $.code.coding[0].code					missing					422    	Observation.code.coding:sofaScore: minimum required = 1, but only found 0 .from ${sofa_score-url}.          Observation.code
	$.code.coding[0].code					${EMPTY}				422    	@value cannot be empty																						Observation.code.coding.0..code
	$.code.coding[0].code					${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        Observation.code.coding.0.
	$.code.coding[0].code					${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.code.coding.0..code

	# invalid Code Coding 0 Display
    $.code.coding[0].display				${EMPTY}				422    	@value cannot be empty																						Observation.code.coding.0..display
	$.code.coding[0].display				${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.code.coding.0..display


008 Create Sofa Score (Invalid/Missing 'effectiveDateTime')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``effectiveDateTime`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create sofa score with ehr reference
    [Tags]              effectiveDateTime

	# FIELD/PATH							VALUE					HTTP	ERROR MESSAGE																								Location
	# 																CODE
	
	# missing attribute
#   $.effectiveDateTime						missing					422    	Observation.effective.x.: minimum required = 1, but only found 0 .from https:/*								Observation
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


009 Create Sofa Score (Invalid/Missing 'component' for missing or empty ground attributes)
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``component`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create sofa score with ehr reference
    [Tags]              valueCodeableConcept


	# FIELD/PATH								                VALUE					HTTP	ERROR MESSAGE																								                                Location
	# 																	                CODE
	
	# missing valueCodeableConcept
    $.component						                            missing					422    	Index 0 out of bounds for length 0
    $.component        						                    ${EMPTY}				422    	This property must be an Array, not a primitive property													                                   Observation.component

	# wrong format
    $.component         						                ${{ [] }}				422    	Array cannot be empty - the property should not be present if it has no values								                                    Observation.component
    $.component         						                ${{ {} }}				422    	Object must have some content																				                                    Observation.component
    $.component         						                ${{ [{}] }}				422    	Observation.component.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.					                                    Observation.component

	# missing array value
    $.component[0] 				                                missing					422    	Index 5 out of bounds for length 5
    $.component[1] 				                                missing					422    	Index 5 out of bounds for length 5
    $.component[2] 				                                missing					422    	Index 5 out of bounds for length 5
    $.component[3] 				                                missing					422    	Index 5 out of bounds for length 5
    $.component[4] 				                                missing					422    	Index 5 out of bounds for length 5
    $.component[5] 				                                missing					422    	Index 5 out of bounds for length 5
    
    # empty array
    $.component[0] 				                                ${EMPTY}				422    	This property must be an Array, not a primitive property                                                                                        Observation.component.0.
    $.component[1] 				                                ${EMPTY}				422    	This property must be an Array, not a primitive property                                                                                        Observation.component.1.
    $.component[2] 				                                ${EMPTY}				422    	This property must be an Array, not a primitive property                                                                                        Observation.component.2.
    $.component[3] 				                                ${EMPTY}				422    	This property must be an Array, not a primitive property                                                                                        Observation.component.3.
    $.component[4] 				                                ${EMPTY}				422    	This property must be an Array, not a primitive property                                                                                        Observation.component.4.
    $.component[5] 				                                ${EMPTY}				422    	This property must be an Array, not a primitive property                                                                                        Observation.component.5.

    # missing component x code
    $.component[0].code 				                        missing					422    	Observation.component.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                     Observation.component.0.
    $.component[1].code 				                        missing					422    	Observation.component.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                     Observation.component.1.
    $.component[2].code 				                        missing					422    	Observation.component.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                     Observation.component.2.
    $.component[3].code 				                        missing					422    	Observation.component.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                     Observation.component.3.
    $.component[4].code 				                        missing					422    	Observation.component.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                     Observation.component.4.
    $.component[5].code 				                        missing					422    	Observation.component.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                     Observation.component.5.

    # empty component x code
    $.component[0].code 				                        ${EMPTY}				422    	Observation.component.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                     Observation.component.0.
    $.component[1].code 				                        ${EMPTY}				422    	Observation.component.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                     Observation.component.1.
    $.component[2].code 				                        ${EMPTY}				422    	Observation.component.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                     Observation.component.2.
    $.component[3].code 				                        ${EMPTY}				422    	Observation.component.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                     Observation.component.3.
    $.component[4].code 				                        ${EMPTY}				422    	Observation.component.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                     Observation.component.4.
    $.component[5].code 				                        ${EMPTY}				422    	Observation.component.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                     Observation.component.5.

    # missing component x valueCodableConcept
    $.component[0].valueCodeableConcept 				        missing					422    	Index 0 out of bounds for length 0
    $.component[1].valueCodeableConcept 				        missing					422    	Index 0 out of bounds for length 0
    $.component[2].valueCodeableConcept 				        missing					422    	Index 0 out of bounds for length 0
    $.component[3].valueCodeableConcept 				        missing					422    	Index 0 out of bounds for length 0
    $.component[4].valueCodeableConcept 				        missing					422    	Index 0 out of bounds for length 0
    $.component[5].valueCodeableConcept				            missing					422    	Index 0 out of bounds for length 0

    # empty component x valueCodableConcept
    $.component[0].valueCodeableConcept 				        ${EMPTY}				422    	This property must be an Object, not a primitive property                                                                                       Observation.component.0..value.x.
    $.component[1].valueCodeableConcept 				        ${EMPTY}				422    	This property must be an Object, not a primitive property                                                                                       Observation.component.1..value.x.
    $.component[2].valueCodeableConcept 				        ${EMPTY}				422    	This property must be an Object, not a primitive property                                                                                       Observation.component.2..value.x.
    $.component[3].valueCodeableConcept 				        ${EMPTY}				422    	This property must be an Object, not a primitive property                                                                                       Observation.component.3..value.x.
    $.component[4].valueCodeableConcept 				        ${EMPTY}				422    	This property must be an Object, not a primitive property                                                                                       Observation.component.4..value.x.
    $.component[5].valueCodeableConcept 				        ${EMPTY}				422    	This property must be an Object, not a primitive property                                                                                       Observation.component.5..value.x.

    # empty component x code coding
    $.component[0].code.coding 	 				                ${EMPTY}				422    	This property must be an Array, not a primitive property                                                                                        Observation.component.0..code.coding
    $.component[1].code.coding 	 				                ${EMPTY}				422    	This property must be an Array, not a primitive property                                                                                        Observation.component.1..code.coding
    $.component[2].code.coding 	 				                ${EMPTY}				422    	This property must be an Array, not a primitive property                                                                                        Observation.component.2..code.coding
    $.component[3].code.coding 	 	 	 				        ${EMPTY}				422    	This property must be an Array, not a primitive property                                                                                        Observation.component.3..code.coding
    $.component[4].code.coding 	 		 				        ${EMPTY}				422    	This property must be an Array, not a primitive property                                                                                        Observation.component.4..code.coding
    $.component[5].code.coding 	 				                ${EMPTY}				422    	This property must be an Array, not a primitive property                                                                                        Observation.component.5..code.coding

    # missing component x valueCodableConcept coding
    $.component[0].valueCodeableConcept.coding 				    missing					422    	Observation.component:respiratorySystem.value.x..coding: minimum required = 1, but only found 0 .from ${sofa_score-url}.                        Observation.component.0..value.ofType.CodeableConcept.
    $.component[1].valueCodeableConcept.coding 				    missing					422    	Observation.component:nervousSystem.value.x..coding: minimum required = 1, but only found 0 .from ${sofa_score-url}.                            Observation.component.1..value.ofType.CodeableConcept.
    $.component[2].valueCodeableConcept.coding 				    missing					422    	Observation.component:cardiovascularSystem.value.x..coding: minimum required = 1, but only found 0 .from ${sofa_score-url}.                     Observation.component.2..value.ofType.CodeableConcept.
    $.component[3].valueCodeableConcept.coding 				    missing					422    	Observation.component:liver.value.x..coding: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                    Observation.component.3..value.ofType.CodeableConcept.
    $.component[4].valueCodeableConcept.coding 				    missing					422    	Observation.component:coagulation.value.x..coding: minimum required = 1, but only found 0 .from ${sofa_score-url}.                              Observation.component.4..value.ofType.CodeableConcept.
    $.component[5].valueCodeableConcept.coding				    missing					422    	Observation.component:kidneys.value.x..coding: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                  Observation.component.5..value.ofType.CodeableConcept.

    # empty component x valueCodableConcept coding
    $.component[0].valueCodeableConcept.coding 				    ${EMPTY}				422    	Observation.component:respiratorySystem.value.x..coding: minimum required = 1, but only found 0 .from ${sofa_score-url}.                        Observation.component.0..value.ofType.CodeableConcept.
    $.component[1].valueCodeableConcept.coding 				    ${EMPTY}				422    	Observation.component:nervousSystem.value.x..coding: minimum required = 1, but only found 0 .from ${sofa_score-url}.                            Observation.component.1..value.ofType.CodeableConcept.
    $.component[2].valueCodeableConcept.coding 				    ${EMPTY}				422    	Observation.component:cardiovascularSystem.value.x..coding: minimum required = 1, but only found 0 .from ${sofa_score-url}.                     Observation.component.2..value.ofType.CodeableConcept.
    $.component[3].valueCodeableConcept.coding 				    ${EMPTY}				422    	Observation.component:liver.value.x..coding: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                    Observation.component.3..value.ofType.CodeableConcept.
    $.component[4].valueCodeableConcept.coding 				    ${EMPTY}				422    	Observation.component:coagulation.value.x..coding: minimum required = 1, but only found 0 .from ${sofa_score-url}.                              Observation.component.4..value.ofType.CodeableConcept.
    $.component[5].valueCodeableConcept.coding				    ${EMPTY}				422    	Observation.component:kidneys.value.x..coding: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                  Observation.component.5..value.ofType.CodeableConcept.


010 Create Sofa Score (Invalid/Missing 'component' for Array value 0)
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``component[0]`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create sofa score with ehr reference
    [Tags]              valueCodeableConcept

	# FIELD/PATH								                VALUE					HTTP	ERROR MESSAGE																								                                Location
	# 																	                CODE

    #invalid component 0 code coding system
	$.component[0].code.coding[0].system						${EMPTY}				422    	@value cannot be empty																													Observation.component.0..code.coding.0..system
#	$.component[0].code.coding[0].system						http://foobar.de		422    	This element does not match any known slice defined in the profile ${sofa_score-url}                    								Observation.component
	$.component[0].code.coding[0].system						${randstring}			422    	Coding.system must be an absolute reference, not a local reference																		Observation.component.0..code.coding.0.
	$.component[0].code.coding[0].system						${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.0..code.coding.0..system

    #invalid component 0 code coding code
	$.component[0].code.coding[0].code							${EMPTY}				422    	@value cannot be empty																													Observation.component.0..code.coding.0..code
#	$.component[0].code.coding[0].code							${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.0..code.coding.0.
	$.component[0].code.coding[0].code							${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.0..code.coding.0..code

    #invalid component 0 code coding display
    $.component[0].code.coding[0].display						${EMPTY}				422    	@value cannot be empty																													Observation.component.0..code.coding.0..display
	$.component[0].code.coding[0].display						${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.0..code.coding.0..display

    #invalid component 0 code text
	$.component[0].code.text									${EMPTY}				422    	@value cannot be empty																													Observation.component.0..code.text
	$.component[0].code.text									${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.0..code.text

    #invalid component 0 valueCodableConcept coding system
	$.component[0].valueCodeableConcept.coding[0].system 		missing					422    	Missing element 'system' - required by fixed value assigned in profile ${sofa_score-url}												Observation.component.0..value.ofType.CodeableConcept..coding.0..system
	$.component[0].valueCodeableConcept.coding[0].system		${EMPTY}				422    	@value cannot be empty																													Observation.component.0..value.ofType.CodeableConcept..coding.0..system
	$.component[0].valueCodeableConcept.coding[0].system		http://foobar.de		422    	Value is 'http://foobar.de' but must be '${sofa_score-url2}'                   															Observation.component.0..value.ofType.CodeableConcept..coding.0..system
	$.component[0].valueCodeableConcept.coding[0].system		${randstring}			422    	Value is '${randstring}' but must be '${sofa_score-url2}'																				Observation.component.0..value.ofType.CodeableConcept..coding.0..system
	$.component[0].valueCodeableConcept.coding[0].system		${randinteger}			422    	Value is '${randinteger}' but must be '${sofa_score-url2}'																				Observation.component.0..value.ofType.CodeableConcept..coding.0..system

    #invalid component 0 valueCodableConcept coding code
	$.component[0].valueCodeableConcept.coding[0].code			missing					422    	Observation.component:respiratorySystem.value.x..coding.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.          	Observation.component.0..value.ofType.CodeableConcept..coding.0.
	$.component[0].valueCodeableConcept.coding[0].code			${EMPTY}				422    	@value cannot be empty																													Observation.component.0..value.ofType.CodeableConcept..coding.0..code
#	$.component[0].valueCodeableConcept.coding[0].code			${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.0..value.ofType.CodeableConcept..coding.0.
	$.component[0].valueCodeableConcept.coding[0].code			${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.0..value.x..coding.0..code

    #invalid component 0 valueCodableConcept coding display
	$.component[0].valueCodeableConcept.coding[0].display		${EMPTY}				422    	@value cannot be empty																													Observation.component.0..value.ofType.CodeableConcept..coding.0..display
	$.component[0].valueCodeableConcept.coding[0].display		${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.0..value.x..coding.0..display

    #invalid component 0 valueCodableConcept text
	$.component[0].valueCodeableConcept.text					${EMPTY}				422    	@value cannot be empty																													Observation.component.0..value.ofType.CodeableConcept..text
	$.component[0].valueCodeableConcept.text					${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.0..value.x..text


011 Create Sofa Score (Invalid/Missing 'component' for Array value 1)
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``component[1]`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create sofa score with ehr reference
    [Tags]              valueCodeableConcept

	# FIELD/PATH								                VALUE					HTTP	ERROR MESSAGE																								                                Location
	# 																	                CODE

    #invalid component 1 code coding system
	$.component[1].code.coding[0].system						${EMPTY}				422    	@value cannot be empty																													Observation.component.1..code.coding.0..system
#	$.component[1].code.coding[0].system						http://foobar.de		422    	This element does not match any known slice defined in the profile ${sofa_score-url}                    								Observation.component
	$.component[1].code.coding[0].system						${randstring}			422    	Coding.system must be an absolute reference, not a local reference																		Observation.component.1..code.coding.0.
	$.component[1].code.coding[0].system						${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.1..code.coding.0..system

    #invalid component 1 code coding code
	$.component[1].code.coding[0].code							${EMPTY}				422    	@value cannot be empty																													Observation.component.1..code.coding.0..code
#	$.component[1].code.coding[0].code							${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.1..code.coding.0.
	$.component[1].code.coding[0].code							${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.1..code.coding.0..code

    #invalid component 1 code coding display
    $.component[1].code.coding[0].display						${EMPTY}				422    	@value cannot be empty																													Observation.component.1..code.coding.0..display
	$.component[1].code.coding[0].display						${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.1..code.coding.0..display

    #invalid component 1 code text
	$.component[1].code.text									${EMPTY}				422    	@value cannot be empty																													Observation.component.1..code.text
	$.component[1].code.text									${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.1..code.text

    #invalid component 1 valueCodableConcept coding system
	$.component[1].valueCodeableConcept.coding[0].system 		missing					422    	Missing element 'system' - required by fixed value assigned in profile ${sofa_score-url}												Observation.component.1..value.ofType.CodeableConcept..coding.0..system
	$.component[1].valueCodeableConcept.coding[0].system		${EMPTY}				422    	@value cannot be empty																													Observation.component.1..value.ofType.CodeableConcept..coding.0..system
	$.component[1].valueCodeableConcept.coding[0].system		http://foobar.de		422    	Value is 'http://foobar.de' but must be '${sofa_score-url2}'                   															Observation.component.1..value.ofType.CodeableConcept..coding.0..system
	$.component[1].valueCodeableConcept.coding[0].system		${randstring}			422    	Value is '${randstring}' but must be '${sofa_score-url2}'																				Observation.component.1..value.ofType.CodeableConcept..coding.0..system
	$.component[1].valueCodeableConcept.coding[0].system		${randinteger}			422    	Value is '${randinteger}' but must be '${sofa_score-url2}'																				Observation.component.1..value.ofType.CodeableConcept..coding.0..system

    #invalid component 1 valueCodableConcept coding code
	$.component[1].valueCodeableConcept.coding[0].code			missing					422    	Observation.component:nervousSystem.value.x..coding.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.          		Observation.component.1..value.ofType.CodeableConcept..coding.0.
	$.component[1].valueCodeableConcept.coding[0].code			${EMPTY}				422    	@value cannot be empty																													Observation.component.1..value.ofType.CodeableConcept..coding.0..code
#	$.component[1].valueCodeableConcept.coding[0].code			${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.1..value.ofType.CodeableConcept..coding.0.
	$.component[1].valueCodeableConcept.coding[0].code			${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.1..value.x..coding.0..code

    #invalid component 1 valueCodableConcept coding display
	$.component[1].valueCodeableConcept.coding[0].display		${EMPTY}				422    	@value cannot be empty																													Observation.component.1..value.ofType.CodeableConcept..coding.0..display
	$.component[1].valueCodeableConcept.coding[0].display		${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.1..value.x..coding.0..display

    #invalid component 1 valueCodableConcept text
	$.component[1].valueCodeableConcept.text					${EMPTY}				422    	@value cannot be empty																													Observation.component.1..value.ofType.CodeableConcept..text
	$.component[1].valueCodeableConcept.text					${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.1..value.x..text


012 Create Sofa Score (Invalid/Missing 'component' for Array value 2)
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``component[2]`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create sofa score with ehr reference
    [Tags]              valueCodeableConcept

	# FIELD/PATH								                VALUE					HTTP	ERROR MESSAGE																								                                Location
	# 																	                CODE

    #invalid component 2 code coding system
	$.component[2].code.coding[0].system						${EMPTY}				422    	@value cannot be empty																													Observation.component.2..code.coding.0..system
#	$.component[2].code.coding[0].system						http://foobar.de		422    	This element does not match any known slice defined in the profile ${sofa_score-url}                    								Observation.component
	$.component[2].code.coding[0].system						${randstring}			422    	Coding.system must be an absolute reference, not a local reference																		Observation.component.2..code.coding.0.
	$.component[2].code.coding[0].system						${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.2..code.coding.0..system

    #invalid component 2 code coding code
	$.component[2].code.coding[0].code							${EMPTY}				422    	@value cannot be empty																													Observation.component.2..code.coding.0..code
#	$.component[2].code.coding[0].code							${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.2..code.coding.0.
	$.component[2].code.coding[0].code							${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.2..code.coding.0..code

    #invalid component 2 code coding display
    $.component[2].code.coding[0].display						${EMPTY}				422    	@value cannot be empty																													Observation.component.2..code.coding.0..display
	$.component[2].code.coding[0].display						${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.2..code.coding.0..display

    #invalid component 2 code text
	$.component[2].code.text									${EMPTY}				422    	@value cannot be empty																													Observation.component.2..code.text
	$.component[2].code.text									${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.2..code.text

    #invalid component 2 valueCodableConcept coding system
	$.component[2].valueCodeableConcept.coding[0].system 		missing					422    	Missing element 'system' - required by fixed value assigned in profile ${sofa_score-url}												Observation.component.2..value.ofType.CodeableConcept..coding.0..system
	$.component[2].valueCodeableConcept.coding[0].system		${EMPTY}				422    	@value cannot be empty																													Observation.component.2..value.ofType.CodeableConcept..coding.0..system
	$.component[2].valueCodeableConcept.coding[0].system		http://foobar.de		422    	Value is 'http://foobar.de' but must be '${sofa_score-url2}'                   															Observation.component.2..value.ofType.CodeableConcept..coding.0..system
	$.component[2].valueCodeableConcept.coding[0].system		${randstring}			422    	Value is '${randstring}' but must be '${sofa_score-url2}'																				Observation.component.2..value.ofType.CodeableConcept..coding.0..system
	$.component[2].valueCodeableConcept.coding[0].system		${randinteger}			422    	Value is '${randinteger}' but must be '${sofa_score-url2}'																				Observation.component.2..value.ofType.CodeableConcept..coding.0..system

    #invalid component 2 valueCodableConcept coding code
	$.component[2].valueCodeableConcept.coding[0].code			missing					422    	Observation.component:cardiovascularSystem.value.x..coding.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.        Observation.component.2..value.ofType.CodeableConcept..coding.0.
	$.component[2].valueCodeableConcept.coding[0].code			${EMPTY}				422    	@value cannot be empty																													Observation.component.2..value.ofType.CodeableConcept..coding.0..code
#	$.component[2].valueCodeableConcept.coding[0].code			${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.2..value.ofType.CodeableConcept..coding.0.
	$.component[2].valueCodeableConcept.coding[0].code			${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.2..value.x..coding.0..code

    #invalid component 2 valueCodableConcept coding display
	$.component[2].valueCodeableConcept.coding[0].display		${EMPTY}				422    	@value cannot be empty																													Observation.component.2..value.ofType.CodeableConcept..coding.0..display
	$.component[2].valueCodeableConcept.coding[0].display		${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.2..value.x..coding.0..display

    #invalid component 2 valueCodableConcept text
	$.component[2].valueCodeableConcept.text					${EMPTY}				422    	@value cannot be empty																													Observation.component.2..value.ofType.CodeableConcept..text
	$.component[2].valueCodeableConcept.text					${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.2..value.x..text


013 Create Sofa Score (Invalid/Missing 'component' for Array value 3)
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``component[3]`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create sofa score with ehr reference
    [Tags]              valueCodeableConcept

	# FIELD/PATH								                VALUE					HTTP	ERROR MESSAGE																								                                Location
	# 																	                CODE

    #invalid component 3 code coding system
	$.component[3].code.coding[0].system						${EMPTY}				422    	@value cannot be empty																													Observation.component.3..code.coding.0..system
#	$.component[3].code.coding[0].system						http://foobar.de		422    	This element does not match any known slice defined in the profile ${sofa_score-url}                    								Observation.component
	$.component[3].code.coding[0].system						${randstring}			422    	Coding.system must be an absolute reference, not a local reference																		Observation.component.3..code.coding.0.
	$.component[3].code.coding[0].system						${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.3..code.coding.0..system

    #invalid component 3 code coding code
	$.component[3].code.coding[0].code							${EMPTY}				422    	@value cannot be empty																													Observation.component.3..code.coding.0..code
#	$.component[3].code.coding[0].code							${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.3..code.coding.0.
	$.component[3].code.coding[0].code							${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.3..code.coding.0..code

    #invalid component 3 code coding display
    $.component[3].code.coding[0].display						${EMPTY}				422    	@value cannot be empty																													Observation.component.3..code.coding.0..display
	$.component[3].code.coding[0].display						${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.3..code.coding.0..display

    #invalid component 3 code text
	$.component[3].code.text									${EMPTY}				422    	@value cannot be empty																													Observation.component.3..code.text
	$.component[3].code.text									${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.3..code.text

    #invalid component 3 valueCodableConcept coding system
	$.component[3].valueCodeableConcept.coding[0].system 		missing					422    	Missing element 'system' - required by fixed value assigned in profile ${sofa_score-url}												Observation.component.3..value.ofType.CodeableConcept..coding.0..system
	$.component[3].valueCodeableConcept.coding[0].system		${EMPTY}				422    	@value cannot be empty																													Observation.component.3..value.ofType.CodeableConcept..coding.0..system
	$.component[3].valueCodeableConcept.coding[0].system		http://foobar.de		422    	Value is 'http://foobar.de' but must be '${sofa_score-url2}'                   															Observation.component.3..value.ofType.CodeableConcept..coding.0..system
	$.component[3].valueCodeableConcept.coding[0].system		${randstring}			422    	Value is '${randstring}' but must be '${sofa_score-url2}'																				Observation.component.3..value.ofType.CodeableConcept..coding.0..system
	$.component[3].valueCodeableConcept.coding[0].system		${randinteger}			422    	Value is '${randinteger}' but must be '${sofa_score-url2}'																				Observation.component.3..value.ofType.CodeableConcept..coding.0..system

    #invalid component 3 valueCodableConcept coding code
	$.component[3].valueCodeableConcept.coding[0].code			missing					422    	Observation.component:liver.value.x..coding.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.        				Observation.component.3..value.ofType.CodeableConcept..coding.0.
	$.component[3].valueCodeableConcept.coding[0].code			${EMPTY}				422    	@value cannot be empty																													Observation.component.3..value.ofType.CodeableConcept..coding.0..code
#	$.component[3].valueCodeableConcept.coding[0].code			${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.3..value.ofType.CodeableConcept..coding.0.
	$.component[3].valueCodeableConcept.coding[0].code			${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.3..value.x..coding.0..code

    #invalid component 3 valueCodableConcept coding display
	$.component[3].valueCodeableConcept.coding[0].display		${EMPTY}				422    	@value cannot be empty																													Observation.component.3..value.ofType.CodeableConcept..coding.0..display
	$.component[3].valueCodeableConcept.coding[0].display		${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.3..value.x..coding.0..display

    #invalid component 3 valueCodableConcept text
	$.component[3].valueCodeableConcept.text					${EMPTY}				422    	@value cannot be empty																													Observation.component.3..value.ofType.CodeableConcept..text
	$.component[3].valueCodeableConcept.text					${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.3..value.x..text


014 Create Sofa Score (Invalid/Missing 'component' for Array value 4)
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``component[4]`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create sofa score with ehr reference
    [Tags]              valueCodeableConcept

	# FIELD/PATH								                VALUE					HTTP	ERROR MESSAGE																								                                Location
	# 																	                CODE

    #invalid component 4 code coding system
	$.component[4].code.coding[0].system						${EMPTY}				422    	@value cannot be empty																													Observation.component.4..code.coding.0..system
#	$.component[4].code.coding[0].system						http://foobar.de		422    	This element does not match any known slice defined in the profile ${sofa_score-url}                    								Observation.component
	$.component[4].code.coding[0].system						${randstring}			422    	Coding.system must be an absolute reference, not a local reference																		Observation.component.4..code.coding.0.
	$.component[4].code.coding[0].system						${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.4..code.coding.0..system

    #invalid component 4 code coding code
	$.component[4].code.coding[0].code							${EMPTY}				422    	@value cannot be empty																													Observation.component.4..code.coding.0..code
#	$.component[4].code.coding[0].code							${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.4..code.coding.0.
	$.component[4].code.coding[0].code							${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.4..code.coding.0..code

    #invalid component 4 code coding display
    $.component[4].code.coding[0].display						${EMPTY}				422    	@value cannot be empty																													Observation.component.4..code.coding.0..display
	$.component[4].code.coding[0].display						${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.4..code.coding.0..display

    #invalid component 4 code text
	$.component[4].code.text									${EMPTY}				422    	@value cannot be empty																													Observation.component.4..code.text
	$.component[4].code.text									${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.4..code.text

    #invalid component 4 valueCodableConcept coding system
	$.component[4].valueCodeableConcept.coding[0].system 		missing					422    	Missing element 'system' - required by fixed value assigned in profile ${sofa_score-url}												Observation.component.4..value.ofType.CodeableConcept..coding.0..system
	$.component[4].valueCodeableConcept.coding[0].system		${EMPTY}				422    	@value cannot be empty																													Observation.component.4..value.ofType.CodeableConcept..coding.0..system
	$.component[4].valueCodeableConcept.coding[0].system		http://foobar.de		422    	Value is 'http://foobar.de' but must be '${sofa_score-url2}'                   															Observation.component.4..value.ofType.CodeableConcept..coding.0..system
	$.component[4].valueCodeableConcept.coding[0].system		${randstring}			422    	Value is '${randstring}' but must be '${sofa_score-url2}'																				Observation.component.4..value.ofType.CodeableConcept..coding.0..system
	$.component[4].valueCodeableConcept.coding[0].system		${randinteger}			422    	Value is '${randinteger}' but must be '${sofa_score-url2}'																				Observation.component.4..value.ofType.CodeableConcept..coding.0..system

    #invalid component 4 valueCodableConcept coding code
	$.component[4].valueCodeableConcept.coding[0].code			missing					422    	Observation.component:coagulation.value.x..coding.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.        			Observation.component.4..value.ofType.CodeableConcept..coding.0.
	$.component[4].valueCodeableConcept.coding[0].code			${EMPTY}				422    	@value cannot be empty																													Observation.component.4..value.ofType.CodeableConcept..coding.0..code
#	$.component[4].valueCodeableConcept.coding[0].code			${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.4..value.ofType.CodeableConcept..coding.0.
	$.component[4].valueCodeableConcept.coding[0].code			${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.4..value.x..coding.0..code

    #invalid component 4 valueCodableConcept coding display
	$.component[4].valueCodeableConcept.coding[0].display		${EMPTY}				422    	@value cannot be empty																													Observation.component.4..value.ofType.CodeableConcept..coding.0..display
	$.component[4].valueCodeableConcept.coding[0].display		${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.4..value.x..coding.0..display

    #invalid component 4 valueCodableConcept text
	$.component[4].valueCodeableConcept.text					${EMPTY}				422    	@value cannot be empty																													Observation.component.4..value.ofType.CodeableConcept..text
	$.component[4].valueCodeableConcept.text					${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.4..value.x..text


015 Create Sofa Score (Invalid/Missing 'component' for Array value 5)
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``component[5]`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create sofa score with ehr reference
    [Tags]              valueCodeableConcept

	# FIELD/PATH								                VALUE					HTTP	ERROR MESSAGE																								                                Location
	# 																	                CODE

    #invalid component 5 code coding system
	$.component[5].code.coding[0].system						${EMPTY}				422    	@value cannot be empty																													Observation.component.5..code.coding.0..system
#	$.component[5].code.coding[0].system						http://foobar.de		422    	This element does not match any known slice defined in the profile ${sofa_score-url}                    								Observation.component
	$.component[5].code.coding[0].system						${randstring}			422    	Coding.system must be an absolute reference, not a local reference																		Observation.component.5..code.coding.0.
	$.component[5].code.coding[0].system						${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.5..code.coding.0..system

    #invalid component 5 code coding code
	$.component[5].code.coding[0].code							${EMPTY}				422    	@value cannot be empty																													Observation.component.5..code.coding.0..code
#	$.component[5].code.coding[0].code							${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.5..code.coding.0.
	$.component[5].code.coding[0].code							${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.5..code.coding.0..code

    #invalid component 5 code coding display
    $.component[5].code.coding[0].display						${EMPTY}				422    	@value cannot be empty																													Observation.component.5..code.coding.0..display
	$.component[5].code.coding[0].display						${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.5..code.coding.0..display

    #invalid component 5 code text
	$.component[5].code.text									${EMPTY}				422    	@value cannot be empty																													Observation.component.5..code.text
	$.component[5].code.text									${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.5..code.text

    #invalid component 5 valueCodableConcept coding system
	$.component[5].valueCodeableConcept.coding[0].system 		missing					422    	Missing element 'system' - required by fixed value assigned in profile ${sofa_score-url}												Observation.component.5..value.ofType.CodeableConcept..coding.0..system
	$.component[5].valueCodeableConcept.coding[0].system		${EMPTY}				422    	@value cannot be empty																													Observation.component.5..value.ofType.CodeableConcept..coding.0..system
	$.component[5].valueCodeableConcept.coding[0].system		http://foobar.de		422    	Value is 'http://foobar.de' but must be '${sofa_score-url2}'                   															Observation.component.5..value.ofType.CodeableConcept..coding.0..system
	$.component[5].valueCodeableConcept.coding[0].system		${randstring}			422    	Value is '${randstring}' but must be '${sofa_score-url2}'																				Observation.component.5..value.ofType.CodeableConcept..coding.0..system
	$.component[5].valueCodeableConcept.coding[0].system		${randinteger}			422    	Value is '${randinteger}' but must be '${sofa_score-url2}'																				Observation.component.5..value.ofType.CodeableConcept..coding.0..system

    #invalid component 5 valueCodableConcept coding code
	$.component[5].valueCodeableConcept.coding[0].code			missing					422    	Observation.component:kidneys.value.x..coding.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.        				Observation.component.5..value.ofType.CodeableConcept..coding.0.
	$.component[5].valueCodeableConcept.coding[0].code			${EMPTY}				422    	@value cannot be empty																													Observation.component.5..value.ofType.CodeableConcept..coding.0..code
#	$.component[5].valueCodeableConcept.coding[0].code			${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.5..value.ofType.CodeableConcept..coding.0.
	$.component[5].valueCodeableConcept.coding[0].code			${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.5..value.x..coding.0..code

    #invalid component 5 valueCodableConcept coding display
	$.component[5].valueCodeableConcept.coding[0].display		${EMPTY}				422    	@value cannot be empty																													Observation.component.5..value.ofType.CodeableConcept..coding.0..display
	$.component[5].valueCodeableConcept.coding[0].display		${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.5..value.x..coding.0..display

    #invalid component 5 valueCodableConcept text
	$.component[5].valueCodeableConcept.text					${EMPTY}				422    	@value cannot be empty																													Observation.component.5..value.ofType.CodeableConcept..text
	$.component[5].valueCodeableConcept.text					${randinteger}			422    	Error parsing JSON: the primitive value must be a string																				Observation.component.5..value.x..text


016 Create Sofa Score (Invalid 'DataAbsentReason' AND 'component')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``DataAbsentReason`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Tags]              DataAbsentReason    not-ready    not-ready_bug

	ehr.create new ehr    				  							000_ehr_status.json
	create with DataAbsentReason		  							DataAbsentReason				create-sofa-score.json
	observation.validate response - 422 (with error message)		422								obs-6: dataAbsentReason SHALL only be present if Observation.value.x. is not present .dataAbsentReason.empty.. or value.empty...			Observation




017 Create Sofa Score (Invalid/Missing 'DataAbsentReason')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``DataAbsentReason`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create sofa score with ehr reference AND data absentreason
    [Tags]              DataAbsentReason

	# FIELD/PATH											VALUE					HTTP	ERROR MESSAGE																								Location
	# 																				CODE

	# missing valueCodeableConcept
	$.component[0].dataAbsentReason							missing					422    	Index 0 out of bounds for length 0
	$.component[1].dataAbsentReason							missing					422    	Index 0 out of bounds for length 0
	$.component[2].dataAbsentReason							missing					422    	Index 0 out of bounds for length 0
	$.component[3].dataAbsentReason							missing					422    	Index 0 out of bounds for length 0
	$.component[4].dataAbsentReason							missing					422    	Index 0 out of bounds for length 0
	$.component[5].dataAbsentReason							missing					422    	Index 0 out of bounds for length 0
	$.component[0].dataAbsentReason							${EMPTY}				422    	This property must be an Object, not a primitive property													Observation.component.0..dataAbsentReason
	$.component[1].dataAbsentReason							${EMPTY}				422    	This property must be an Object, not a primitive property													Observation.component.1..dataAbsentReason
	$.component[2].dataAbsentReason							${EMPTY}				422    	This property must be an Object, not a primitive property													Observation.component.2..dataAbsentReason
	$.component[3].dataAbsentReason							${EMPTY}				422    	This property must be an Object, not a primitive property													Observation.component.3..dataAbsentReason
	$.component[4].dataAbsentReason							${EMPTY}				422    	This property must be an Object, not a primitive property													Observation.component.4..dataAbsentReason
	$.component[5].dataAbsentReason							${EMPTY}				422    	This property must be an Object, not a primitive property													Observation.component.5..dataAbsentReason

	# wrong format
	$.component[0].dataAbsentReason							${{ [] }}				422    	This property must be an Object, not an array																Observation.component.0..dataAbsentReason
	$.component[1].dataAbsentReason							${{ [] }}				422    	This property must be an Object, not an array																Observation.component.1..dataAbsentReason
	$.component[2].dataAbsentReason							${{ [] }}				422    	This property must be an Object, not an array																Observation.component.2..dataAbsentReason
	$.component[3].dataAbsentReason							${{ [] }}				422    	This property must be an Object, not an array																Observation.component.3..dataAbsentReason
	$.component[4].dataAbsentReason							${{ [] }}				422    	This property must be an Object, not an array																Observation.component.4..dataAbsentReason
	$.component[5].dataAbsentReason							${{ [] }}				422    	This property must be an Object, not an array																Observation.component.5..dataAbsentReason
	$.component[0].dataAbsentReason							${{ {} }}				422    	Object must have some content																				Observation.component.0..dataAbsentReason
	$.component[1].dataAbsentReason							${{ {} }}				422    	Object must have some content																				Observation.component.1..dataAbsentReason
	$.component[2].dataAbsentReason							${{ {} }}				422    	Object must have some content																				Observation.component.2..dataAbsentReason
	$.component[3].dataAbsentReason							${{ {} }}				422    	Object must have some content																				Observation.component.3..dataAbsentReason
	$.component[4].dataAbsentReason							${{ {} }}				422    	Object must have some content																				Observation.component.4..dataAbsentReason
	$.component[5].dataAbsentReason							${{ {} }}				422    	Object must have some content																				Observation.component.5..dataAbsentReason
	$.component[0].dataAbsentReason							${{ [{}] }}				422    	This property must be an Object, not an array																Observation.component.0..dataAbsentReason
	$.component[1].dataAbsentReason							${{ [{}] }}				422    	This property must be an Object, not an array																Observation.component.1..dataAbsentReason
	$.component[2].dataAbsentReason							${{ [{}] }}				422    	This property must be an Object, not an array																Observation.component.2..dataAbsentReason
	$.component[3].dataAbsentReason							${{ [{}] }}				422    	This property must be an Object, not an array																Observation.component.3..dataAbsentReason
	$.component[4].dataAbsentReason							${{ [{}] }}				422    	This property must be an Object, not an array																Observation.component.4..dataAbsentReason
	$.component[5].dataAbsentReason							${{ [{}] }}				422    	This property must be an Object, not an array																Observation.component.5..dataAbsentReason

	# missing coding
	$.component[0].dataAbsentReason.coding					missing					422    	Index 0 out of bounds for length 0
	$.component[1].dataAbsentReason.coding					missing					422    	Index 0 out of bounds for length 0
	$.component[2].dataAbsentReason.coding					missing					422    	Index 0 out of bounds for length 0
	$.component[3].dataAbsentReason.coding					missing					422    	Index 0 out of bounds for length 0
	$.component[4].dataAbsentReason.coding					missing					422    	Index 0 out of bounds for length 0
	$.component[5].dataAbsentReason.coding					missing					422    	Index 0 out of bounds for length 0
	$.component[0].dataAbsentReason							${EMPTY}				422    	This property must be an Object, not a primitive property													Observation.component.0..dataAbsentReason
	$.component[1].dataAbsentReason							${EMPTY}				422     This property must be an Object, not a primitive property													Observation.component.1..dataAbsentReason
	$.component[2].dataAbsentReason							${EMPTY}				422    	This property must be an Object, not a primitive property													Observation.component.2..dataAbsentReason
	$.component[3].dataAbsentReason							${EMPTY}				422    	This property must be an Object, not a primitive property													Observation.component.3..dataAbsentReason
	$.component[4].dataAbsentReason							${EMPTY}				422    	This property must be an Object, not a primitive property													Observation.component.4..dataAbsentReason
	$.component[5].dataAbsentReason							${EMPTY}				422    	This property must be an Object, not a primitive property													Observation.component.5..dataAbsentReason

	# invalid system
	$.component[0].dataAbsentReason.coding[0].system		missing					422    	Index 0 out of bounds for length 0
	$.component[1].dataAbsentReason.coding[0].system		missing					422     Index 0 out of bounds for length 0
	$.component[2].dataAbsentReason.coding[0].system		missing					422    	Index 0 out of bounds for length 0
	$.component[3].dataAbsentReason.coding[0].system		missing					422    	Index 0 out of bounds for length 0
	$.component[4].dataAbsentReason.coding[0].system		missing					422    	Index 0 out of bounds for length 0
	$.component[5].dataAbsentReason.coding[0].system		missing					422    	Index 0 out of bounds for length 0
	$.component[0].dataAbsentReason.coding[0].system		${EMPTY}				422    	@value cannot be empty																						Observation.component.0..dataAbsentReason.coding.0..system
	$.component[1].dataAbsentReason.coding[0].system		${EMPTY}				422     @value cannot be empty																						Observation.component.1..dataAbsentReason.coding.0..system
	$.component[2].dataAbsentReason.coding[0].system		${EMPTY}				422    	@value cannot be empty																						Observation.component.2..dataAbsentReason.coding.0..system
	$.component[3].dataAbsentReason.coding[0].system		${EMPTY}				422    	@value cannot be empty																						Observation.component.3..dataAbsentReason.coding.0..system
	$.component[4].dataAbsentReason.coding[0].system		${EMPTY}				422    	@value cannot be empty																						Observation.component.4..dataAbsentReason.coding.0..system
	$.component[5].dataAbsentReason.coding[0].system		${EMPTY}				422    	@value cannot be empty																						Observation.component.5..dataAbsentReason.coding.0..system
	$.component[0].dataAbsentReason.coding[0].system		${randstring}			422    	Coding.system must be an absolute reference, not a local reference											Observation.component.0..dataAbsentReason.coding.0.
	$.component[1].dataAbsentReason.coding[0].system		${randstring}			422     Coding.system must be an absolute reference, not a local reference											Observation.component.1..dataAbsentReason.coding.0.
	$.component[2].dataAbsentReason.coding[0].system		${randstring}			422    	Coding.system must be an absolute reference, not a local reference											Observation.component.2..dataAbsentReason.coding.0.
	$.component[3].dataAbsentReason.coding[0].system		${randstring}			422    	Coding.system must be an absolute reference, not a local reference											Observation.component.3..dataAbsentReason.coding.0.
	$.component[4].dataAbsentReason.coding[0].system		${randstring}			422    	Coding.system must be an absolute reference, not a local reference											Observation.component.4..dataAbsentReason.coding.0.
	$.component[5].dataAbsentReason.coding[0].system		${randstring}			422    	Coding.system must be an absolute reference, not a local reference											Observation.component.5..dataAbsentReason.coding.0.
	$.component[0].dataAbsentReason.coding[0].system		${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.0..dataAbsentReason.coding.0..system
	$.component[1].dataAbsentReason.coding[0].system		${randinteger}			422     Error parsing JSON: the primitive value must be a string													Observation.component.1..dataAbsentReason.coding.0..system
	$.component[2].dataAbsentReason.coding[0].system		${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.2..dataAbsentReason.coding.0..system
	$.component[3].dataAbsentReason.coding[0].system		${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.3..dataAbsentReason.coding.0..system
	$.component[4].dataAbsentReason.coding[0].system		${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.4..dataAbsentReason.coding.0..system
	$.component[5].dataAbsentReason.coding[0].system		${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.5..dataAbsentReason.coding.0..system
	$.component[0].dataAbsentReason.coding[0].system		http://foobar.de		422    	Index 0 out of bounds for length 0
	$.component[1].dataAbsentReason.coding[0].system		http://foobar.de		422     Index 0 out of bounds for length 0
	$.component[2].dataAbsentReason.coding[0].system		http://foobar.de		422    	Index 0 out of bounds for length 0
	$.component[3].dataAbsentReason.coding[0].system		http://foobar.de		422    	Index 0 out of bounds for length 0
	$.component[4].dataAbsentReason.coding[0].system		http://foobar.de		422    	Index 0 out of bounds for length 0
	$.component[5].dataAbsentReason.coding[0].system		http://foobar.de		422    	Index 0 out of bounds for length 0

	# invalid code
	$.component[0].dataAbsentReason.coding[0].code			missing					422    	Index 0 out of bounds for length 0
	$.component[1].dataAbsentReason.coding[0].code			missing					422     Index 0 out of bounds for length 0
	$.component[2].dataAbsentReason.coding[0].code			missing					422    	Index 0 out of bounds for length 0
	$.component[3].dataAbsentReason.coding[0].code			missing					422    	Index 0 out of bounds for length 0
	$.component[4].dataAbsentReason.coding[0].code			missing					422    	Index 0 out of bounds for length 0
	$.component[5].dataAbsentReason.coding[0].code			missing					422    	Index 0 out of bounds for length 0
	$.component[0].dataAbsentReason.coding[0].code			${EMPTY}				422    	@value cannot be empty																						Observation.component.0..dataAbsentReason.coding.0..code
	$.component[1].dataAbsentReason.coding[0].code			${EMPTY}				422     @value cannot be empty																						Observation.component.1..dataAbsentReason.coding.0..code
	$.component[2].dataAbsentReason.coding[0].code			${EMPTY}				422    	@value cannot be empty																						Observation.component.2..dataAbsentReason.coding.0..code
	$.component[3].dataAbsentReason.coding[0].code			${EMPTY}				422    	@value cannot be empty																						Observation.component.3..dataAbsentReason.coding.0..code
	$.component[4].dataAbsentReason.coding[0].code			${EMPTY}				422    	@value cannot be empty																						Observation.component.4..dataAbsentReason.coding.0..code
	$.component[5].dataAbsentReason.coding[0].code			${EMPTY}				422    	@value cannot be empty																						Observation.component.5..dataAbsentReason.coding.0..code
	$.component[0].dataAbsentReason.coding[0].code			${randstring}			422    	Index 0 out of bounds for length 0
	$.component[1].dataAbsentReason.coding[0].code			${randstring}			422     Index 0 out of bounds for length 0
	$.component[2].dataAbsentReason.coding[0].code			${randstring}			422    	Index 0 out of bounds for length 0
	$.component[3].dataAbsentReason.coding[0].code			${randstring}			422    	Index 0 out of bounds for length 0
	$.component[4].dataAbsentReason.coding[0].code			${randstring}			422    	Index 0 out of bounds for length 0
	$.component[5].dataAbsentReason.coding[0].code			${randstring}			422    	Index 0 out of bounds for length 0
	$.component[0].dataAbsentReason.coding[0].code			${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.0..dataAbsentReason.coding.0..code
	$.component[1].dataAbsentReason.coding[0].code			${randinteger}			422     Error parsing JSON: the primitive value must be a string													Observation.component.1..dataAbsentReason.coding.0..code
	$.component[2].dataAbsentReason.coding[0].code			${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.2..dataAbsentReason.coding.0..code
	$.component[3].dataAbsentReason.coding[0].code			${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.3..dataAbsentReason.coding.0..code
	$.component[4].dataAbsentReason.coding[0].code			${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.4..dataAbsentReason.coding.0..code
	$.component[5].dataAbsentReason.coding[0].code			${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.5..dataAbsentReason.coding.0..code

	# invalid display
	$.component[0].dataAbsentReason.coding[0].display		${EMPTY}				422    	@value cannot be empty																						Observation.component.0..dataAbsentReason.coding.0..display
	$.component[1].dataAbsentReason.coding[0].display		${EMPTY}				422     @value cannot be empty																						Observation.component.1..dataAbsentReason.coding.0..display
	$.component[2].dataAbsentReason.coding[0].display		${EMPTY}				422    	@value cannot be empty																						Observation.component.2..dataAbsentReason.coding.0..display
	$.component[3].dataAbsentReason.coding[0].display		${EMPTY}				422    	@value cannot be empty																						Observation.component.3..dataAbsentReason.coding.0..display
	$.component[4].dataAbsentReason.coding[0].display		${EMPTY}				422    	@value cannot be empty																						Observation.component.4..dataAbsentReason.coding.0..display
	$.component[5].dataAbsentReason.coding[0].display		${EMPTY}				422    	@value cannot be empty																						Observation.component.5..dataAbsentReason.coding.0..display
	$.component[0].dataAbsentReason.coding[0].display		${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.0..dataAbsentReason.coding.0..display
	$.component[1].dataAbsentReason.coding[0].display		${randinteger}			422     Error parsing JSON: the primitive value must be a string													Observation.component.1..dataAbsentReason.coding.0..display
	$.component[2].dataAbsentReason.coding[0].display		${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.2..dataAbsentReason.coding.0..display
	$.component[3].dataAbsentReason.coding[0].display		${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.3..dataAbsentReason.coding.0..display
	$.component[4].dataAbsentReason.coding[0].display		${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.4..dataAbsentReason.coding.0..display
	$.component[5].dataAbsentReason.coding[0].display		${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.5..dataAbsentReason.coding.0..display

	# invalid text
	$.component[0].dataAbsentReason.text					${EMPTY}				422    	@value cannot be empty																						Observation.component.0..dataAbsentReason.text
	$.component[1].dataAbsentReason.text					${EMPTY}				422     @value cannot be empty																						Observation.component.1..dataAbsentReason.text
	$.component[2].dataAbsentReason.text					${EMPTY}				422    	@value cannot be empty																						Observation.component.2..dataAbsentReason.text
	$.component[3].dataAbsentReason.text					${EMPTY}				422    	@value cannot be empty																						Observation.component.3..dataAbsentReason.text
	$.component[4].dataAbsentReason.text					${EMPTY}				422    	@value cannot be empty																						Observation.component.4..dataAbsentReason.text
	$.component[5].dataAbsentReason.text					${EMPTY}				422    	@value cannot be empty																						Observation.component.5..dataAbsentReason.text
	$.component[0].dataAbsentReason.text					${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.0..dataAbsentReason.text
	$.component[1].dataAbsentReason.text					${randinteger}			422     Error parsing JSON: the primitive value must be a string													Observation.component.1..dataAbsentReason.text
	$.component[2].dataAbsentReason.text					${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.2..dataAbsentReason.text
	$.component[3].dataAbsentReason.text					${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.3..dataAbsentReason.text
	$.component[4].dataAbsentReason.text					${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.4..dataAbsentReason.text
	$.component[5].dataAbsentReason.text					${randinteger}			422    	Error parsing JSON: the primitive value must be a string													Observation.component.5..dataAbsentReason.text


018 Create Sofa Score (invalid multi)
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-sofa-score.json_\n\n
	...                 3. *UPDATE* values for attributes \n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status \n\n
    ...                 6. *VALIDATE* outcome against diagnostic text (english + german)
    [Tags]             multi
    [Template]         create sofa score JSON
#|  resourceType   |          		ID   			|           meta         						|	  status  	|                         				category                           				   |                                        									code          								|         subject                    |	  DateTime	   |                      			     														component			           		                                                												|  R.-Code  |                                                             diagnostics 							                                              |   location
#|                 |                				|  available  | 			profile  			|               |  available  |  codingavailable  |  	   system  			 |    	      code         	   |  available  |  coding available  |  			0.system	  		|  		0.code	  |    		   0.display 		    |  available  |   Identifier-value   |                 |  available  |		0 code  	|     0 code coding    |        0  system     	 |  	0	code      	   |       0 valueCodableConcept      |      0 vCC coding     |     vCC 0 system        |        vCC 0 Code             |           |              				                                    ENG				                                                                  |

# all attributes invalid for component
    Observation    	        sofa-score  			   	 true         ${sofa_score-url}			   		final		     true             true             ${category-url}         			survey          		true			   true		  		 ${code-url}			  		  06		         sofa score            			true    		 	valid      		  2020-02-25		  true		    true	       	true					${EMPTY}	            	${EMPTY}    	        		true			    			true				  ${EMPTY}						${EMPTY}	 			422          @value cannot be empty                                                                                                                              Observation.component.0..value.ofType.CodeableConcept..coding.0..code
    Observation    	        sofa-score  			  	 true         ${sofa_score-url}			   		final		     true             true             ${category-url}         			survey          		true			   true		  		 ${code-url}			  		  06		         sofa score            			true    		 	valid      		  2020-02-25		  true		    true           	true					${1234}	               		test      	        			true			    			true				  ${1234}						test		 			422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.component.0..value.x..coding.0..system
    Observation    	        sofa-score  			     true         ${sofa_score-url}			   		final		     true             true             ${category-url}         			survey          		true			   true		  		 ${code-url}			  		  06		         sofa score            			true    		 	valid      		  2020-02-25		  true		    true	       	true					http://google.com       	${EMPTY}	                  	true			    			true				  http://google.com           	${EMPTY}	 			422          ele-1: All FHIR elements must have a @value or children .hasValue.. or .children...count.. > id.count....                                           Observation.component.0..value.ofType.CodeableConcept..coding.0..code
    Observation    	        sofa-score  			     true         ${sofa_score-url}			   		final		     true             true             ${category-url}         			survey          		true			   true		  		 ${code-url}			  		  06		         sofa score            			true    		 	valid      		  2020-02-25		  true		    true	       	true					missing	               		${EMPTY}             			true			    			true				  missing	               	    ${EMPTY}	 			422          @value cannot be empty                                                                                                                              Observation.component.0..value.ofType.CodeableConcept..coding.0..code
    Observation    	        sofa-score  			     true         ${sofa_score-url}			   		final		     true             true             ${category-url}         			survey          		true			   true		  		 ${code-url}			  		  06		         sofa score            			true    		 	valid      		  2020-02-25		  true		    true	       	true					${1234}	               		missing 	            		true			    			true				  ${1234}	               		missing		 			422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.component.0..value.x..coding.0..system

# all attributes invalid for code
    Observation    	        sofa-score  		         true         ${sofa_score-url}			   		final		     true             true             ${category-url}         			survey          		true			   false	  		 ${code-url}			  		  06		         sofa score           			true    		 	valid      		  2020-02-25		  true		    true	       	true                   ${sofa_score-url2}	    	resp	    	   				true			    			true                  ${sofa_score-url2}			resp3		 			422          Observation.code.coding: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                            Observation.code
    Observation    	        sofa-score  			     true         ${sofa_score-url}			   		final		     true             true             ${category-url}         			survey          		true			   true			  	 ${EMPTY}             	  		  ${EMPTY}           ${EMPTY}              			true    		 	valid      		  2020-02-25		  true		    true	       	true                   ${sofa_score-url2}	    	resp	    	   				true			    			true                  ${sofa_score-url2}			resp3		 			422          @value cannot be empty                                                                                                                          	 Observation.code.coding.0..display
    Observation    	        sofa-score  			     true         ${sofa_score-url}			   		final		     true             true             ${category-url}         			survey          		true			   true			  	 http://google.com		  		  ${12345}           missing               			true    		 	valid      		  2020-02-25		  true		    true	       	true                   ${sofa_score-url2}	    	resp	    	   				true			    			true                  ${sofa_score-url2}			resp3		 			422          Observation.code.coding:sofaScore: minimum required = 1, but only found 0 .from ${sofa_score-url}.		                                     		 Observation.code
    Observation    	        sofa-score  			     true         ${sofa_score-url}			   		final		     true             true             ${category-url}         			survey          		true			   true			  	 test            		  		  missing            ${12345}              			true    		 	valid      		  2020-02-25		  true		    true	       	true                   ${sofa_score-url2}	    	resp	    	   				true			    			true                  ${sofa_score-url2}			resp3		 			422          Coding.system must be an absolute reference, not a local reference                                                                                  Observation.code.coding.0.
    Observation    	        sofa-score  			     true         ${sofa_score-url}			   		final		     true             true             ${category-url}         			survey          		true			   true			  	 missing         		  	      test               test1234              			true    		 	valid      		  2020-02-25	      true		    true	       	true                   ${sofa_score-url2}	    	resp	    	   				true			    			true                  ${sofa_score-url2}			resp3		 			422          A code with no system has no defined meaning. A system should be provided                                                                           Observation.code.coding.0.

# all attributes invalid for category
	Observation    	        sofa-score  		         true         ${sofa_score-url}			   		final		     true             true             http://google.com         		test                    true			   true		  		 ${code-url}			  		  06		         sofa score            			true    		 	valid      		  2020-02-25		  true		    true	       	true                   ${sofa_score-url2}	    	resp	    	   				true			    			true                  ${sofa_score-url2}			resp3		 			422     	 Observation.category.coding:survey: minimum required = 1, but only found 0 .from ${sofa_score-url}.										 		 Observation.category.0.
    Observation    	        sofa-score  			     true         ${sofa_score-url}			   		final		     true             true             missing                 			${EMPTY}                true			   true			  	 ${code-url}			  		  06		         sofa score            			true    		 	valid      		  2020-02-25		  true		    true	       	true                   ${sofa_score-url2}	    	resp	    	   				true			    			true                  ${sofa_score-url2}			resp3		 			422          @value cannot be empty                                                                                                                              Observation.category.0..coding.0..code
    Observation    	        sofa-score  			     true         ${sofa_score-url}			   		final		     true             true             ${EMPTY}                			${12345}                true			   true			  	 ${code-url}			  		  06		         sofa score            			true    		 	valid      		  2020-02-25		  true		    true	       	true                   ${sofa_score-url2}	    	resp	    	   				true			    			true                  ${sofa_score-url2}			resp3		 			422          @value cannot be empty                                                                                                                              Observation.category.0..coding.0..system
    Observation    	        sofa-score  			     true         ${sofa_score-url}			   		final		     true             true             ${12345}                         missing                 true			   true			     ${code-url}			  		  06		         sofa score            			true    		 	valid      		  2020-02-25		  true		    true	       	true                   ${sofa_score-url2}	    	resp	    	   				true			    			true                  ${sofa_score-url2}			resp3		 			422          Error parsing JSON: the primitive value must be a string                                                                                            Observation.category.0..coding.0..system
    Observation    	        sofa-score  			     true         ${sofa_score-url}			   		final		     true             true             test                             ${EMPTY}                true			   true			  	 ${code-url}			  		  06		         sofa score            			true    		 	valid      		  2020-02-25		  true		    true	       	true                   ${sofa_score-url2}	    	resp	    	   				true			    			true                  ${sofa_score-url2}			resp3		 			422          @value cannot be empty                                                                                                                				 Observation.category.0..coding.0..code

# mix invalid attributes
    Observation    	        sofa-score  			     true         ${sofa_score-url}			  		final		     true             true           ${1234}                            ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}            ${1234}               			true    		 	valid      		  2020-02-25		  true		    true	       	true				   ${1234}	               		${1234}              			true			    			true				  ${1234}	               		${1234}		 			422          Error parsing JSON: the primitive value must be a string                                                                                        	 Observation.code.coding.0..display
    ${1234}      	        sofa-score  			     true         ${sofa_score-url}			  		final		     true             true           ${1234}                            ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}            ${1234}               			true    		 	valid      		  2020-02-25		  true		    true	       	true				   ${1234}	               		${1234}              			true			    			true				  ${1234}	               		${1234}		 			422          This does not appear to be a FHIR resource .unknown name '1234'.                                                                               	 1234
    Observation    	        ${1234}      			     true         ${sofa_score-url}			  		final		     true             true           ${1234}                            ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}            ${1234}               			true    		 	valid      		  2020-02-25		  true		    true	       	true				   ${1234}	               		${1234}              			true			    			true				  ${1234}	               		${1234}		 			422          Error parsing JSON: the primitive value must be a string                                                                                       	 Observation.id
    Observation    	        sofa-score  			     false        ${sofa_score-url}			  		final		     true             true           ${1234}                            ${1234}                 true			   true			  	 ${1234}          		  	      ${1234}            ${1234}               			true    		 	valid      		  2020-02-25		  true		    true	       	true				   ${1234}	               		${1234}              			true			    			true				  ${1234}	               		${1234}		 			422          Error parsing JSON: the primitive value must be a string	                                                                                  		 Observation.category.0..coding.0..code
    Observation    	        sofa-score  			     true         ${1234}                	  		final		     true             true           ${1234}                            ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}            ${1234}               			true    		 	valid      		  2020-02-25		  true		    true	       	true				   ${1234}	               		${1234}              			true			    			true				  ${1234}	               		${1234}		 			422          Profile reference '1234' could not be resolved, so has not been checked                                                                        	 Observation.meta.profile.0.
    Observation    	        sofa-score  			     true         ${sofa_score-url}			  		${1234}	     	 true             true           ${1234}                            ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}            ${1234}              			true    		 	valid      		  2020-02-25		  true		    true	       	true				   ${1234}	               		${1234}              			true			    			true				  ${1234}	               		${1234}		 			422          Error parsing JSON: the primitive value must be a string                                                                                       	 Observation.status
    Observation    	        sofa-score  			     true         ${sofa_score-url}			  		final  	         false            true           ${1234}                            ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}            ${1234}               			true    		 	valid      		  2020-02-25		  true		    true	       	true				   ${1234}	               		${1234}              			true			    			true				  ${1234}	               		${1234}		 			422          Error parsing JSON: the primitive value must be a string                                                                                       	 Observation.code.coding.0..system
    Observation    	        sofa-score  			     true         ${sofa_score-url}			  		final  	         true             false          ${1234}                            ${1234}                 true			   true			  	 ${1234}          		  		  ${1234}            ${1234}               			true    		 	valid      		  2020-02-25		  true		    true	       	true				   ${1234}	               		${1234}              			true			    			true				  ${1234}	               		${1234}		 			422          Object must have some content                                                                                                                  	 Observation.category.0.
    Observation    	        sofa-score  			     true         ${sofa_score-url}			  		final  	         true             true           ${1234}                            ${1234}                 false			   true			  	 ${1234}          		  	      ${1234}            ${1234}               			true    		 	valid      		  2020-02-25		  true		    true	       	true				   ${1234}	               		${1234}              			true			    			true				  ${1234}	               		${1234}		 			422          Observation.code: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                              	 Observation
    Observation    	        sofa-score  			     true         ${sofa_score-url}			  		final  	         true             true           ${1234}                            ${1234}                 true			   false		     ${1234}          		  		  ${1234}            ${1234}               			true    		 	valid      		  2020-02-25		  true		    true	       	true				   ${1234}	               		${1234}              			true			    			true				  ${1234}	               		${1234}		 			422          Observation.code.coding: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                  			 Observation.code
    Observation    	        sofa-score  			     true         ${sofa_score-url}			  		final  	         true             true           ${1234}                            ${1234}                 true			   true 			 ${1234}          		  		  ${1234}            ${1234}               			false    	 	    valid      		  2020-02-25		  true		    true	       	true				   ${1234}	               		${1234}              			true			    			true				  ${1234}	               		${1234}		 			422          Observation.subject: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                                Observation
    Observation    	        sofa-score  			     true         ${sofa_score-url}			  		final  	         true             true           ${1234}                            ${1234}                 true			   false		     ${1234}          		  		  ${1234}            ${1234}               			true    		 	test      		  2020-02-25		  true		    true	       	true				   ${1234}	               		${1234}              			true			    			true				  ${1234}	               		${1234}		 			422          Observation.code.coding: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                       	 Observation.code
    Observation    	        sofa-score  			     true         ${sofa_score-url}			  		final  	         true             true           ${1234}                            ${1234}                 true			   false			 ${1234}          		  		  ${1234}            ${1234}               			true    		 	valid      		  ${12345}		      true		    true	       	true				   ${1234}	               		${1234}              			true			    			true				  ${1234}	               		${1234}		 			422          Not a valid date/time .12345.                                                                                                                       Observation.effective.ofType.dateTime.
    Observation    	        sofa-score  			     true         ${sofa_score-url}			  		final  	         true             true           ${1234}                            ${1234}                 true			   false			 ${1234}          		  		  ${1234}            ${1234}               			true    		 	valid      		  2020-02-25		  false		    true	       	true				   ${1234}	               		${1234}              			true			    			true				  ${1234}	               		${1234}		 			422          Observation.code.coding: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                            Observation.code
    Observation    	        sofa-score  			     true         ${sofa_score-url}			  		final  	         true             true           ${1234}                            ${1234}                 true			   false			 ${1234}          		  		  ${1234}            ${1234}               			true    		 	valid      		  2020-02-25		  true		    false	       	true				   ${1234}	               		${1234}         	 			true			    			true                  ${1234}	               		${1234}		 			422          Observation.code.coding: minimum required = 1, but only found 0 .from ${sofa_score-url}.                                                            Observation.component.0.
    Observation    	        sofa-score  			     true         ${sofa_score-url}			  		final  	         true             true           ${1234}                            ${1234}                 true			   false			 ${1234}          		  		  ${1234}            ${1234}               			true    		 	valid      		  2020-02-25		  true		    true	       	false                  ${1234}	               		${1234}         	 			true			    			true                  ${1234}	               		${1234}		 			422          Error parsing JSON: the primitive value must be a string                                                                                      		 Observation.category.0..coding.0..code
    Observation    	        sofa-score  			     true         ${sofa_score-url}			  		final  	         true             true           ${1234}                            ${1234}                 true			   false			 ${1234}          		  		  ${1234}            ${1234}               			true    		 	valid      		  2020-02-25		  true		    true	       	true                   ${1234}	               		${1234}         	 			false			    			true                  ${1234}	               		${1234}		 			422          This element does not match any known slice defined in the profile ${sofa_score-url}                                                        		 Observation.component.0.
    Observation    	        sofa-score  			     true         ${sofa_score-url}			  		final  	         true             true           ${1234}                            ${1234}                 true			   false			 ${1234}          		  		  ${1234}            ${1234}               			true    		 	valid      		  2020-02-25		  true		    true	       	true                   ${1234}	               		${1234}         	 			true			    			false                 ${1234}	               		${1234}		 			422          This element does not match any known slice defined in the profile ${sofa_score-url}                                                        		 Observation.component.0.


#--------------------------------------------------------------------------------------------------------------------------------------------------------------------
# BUG TRACE
#--------------------------------------------------------------------------------------------------------------------------------------------------------------------

BUG TRACE 01 Create Sofa Score (Invalid/Missing 'effectiveDateTime')
	[Documentation]		Belongs to TC 008! Remove separation when it's fixed!
	[Template]			create sofa score with ehr reference
    [Tags]              effectiveDateTime    not-ready    not-ready_bug

	# FIELD/PATH							VALUE					HTTP	ERROR MESSAGE																								Location
	# 																CODE
	
	# missing attribute
   $.effectiveDateTime						missing					422    	Observation.effective.x.: minimum required = 1, but only found 0 .from https:/*								Observation


BUG TRACE 03 Create Sofa Score (Invalid/Missing 'component' for Array value 0)
	[Documentation]		Belongs to TC 010! Remove separation when it's fixed!
	[Template]			create sofa score with ehr reference
    [Tags]              valueCodeableConcept    not-ready    not-ready_bug

   #invalid component 0 code coding system
	$.component[0].code.coding[0].system						http://foobar.de		422    	This element does not match any known slice defined in the profile ${sofa_score-url}                    								Observation.component

    #invalid component 0 code coding code
	$.component[0].code.coding[0].code							${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.0..code.coding.0.

    #invalid component 0 valueCodableConcept coding code
	$.component[0].valueCodeableConcept.coding[0].code			${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.0..value.ofType.CodeableConcept..coding.0.


BUG TRACE 04 Create Sofa Score (Invalid/Missing 'component' for Array value 1)
	[Documentation]		Belongs to TC 011! Remove separation when it's fixed!
	[Template]			create sofa score with ehr reference
    [Tags]              valueCodeableConcept    not-ready    not-ready_bug

   #invalid component 1 code coding system
	$.component[1].code.coding[0].system						http://foobar.de		422    	This element does not match any known slice defined in the profile ${sofa_score-url}                    								Observation.component

    #invalid component 1 code coding code
	$.component[1].code.coding[0].code							${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.1..code.coding.0.

    #invalid component 1 valueCodableConcept coding code
	$.component[1].valueCodeableConcept.coding[0].code			${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.1..value.ofType.CodeableConcept..coding.0.


BUG TRACE 05 Create Sofa Score (Invalid/Missing 'component' for Array value 2)
	[Documentation]		Belongs to TC 012! Remove separation when it's fixed!
	[Template]			create sofa score with ehr reference
    [Tags]              valueCodeableConcept    not-ready    not-ready_bug

   #invalid component 2 code coding system
	$.component[2].code.coding[0].system						http://foobar.de		422    	This element does not match any known slice defined in the profile ${sofa_score-url}                    								Observation.component

    #invalid component 2 code coding code
	$.component[2].code.coding[0].code							${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.2..code.coding.0.

    #invalid component 2 valueCodableConcept coding code
	$.component[2].valueCodeableConcept.coding[0].code			${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.2..value.ofType.CodeableConcept..coding.0.


BUG TRACE 06 Create Sofa Score (Invalid/Missing 'component' for Array value 3)
	[Documentation]		Belongs to TC 013! Remove separation when it's fixed!
	[Template]			create sofa score with ehr reference
    [Tags]              valueCodeableConcept    not-ready    not-ready_bug

   #invalid component 3 code coding system
	$.component[3].code.coding[0].system						http://foobar.de		422    	This element does not match any known slice defined in the profile ${sofa_score-url}                    								Observation.component

    #invalid component 3 code coding code
	$.component[3].code.coding[0].code							${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.3..code.coding.0.

    #invalid component 3 valueCodableConcept coding code
	$.component[3].valueCodeableConcept.coding[0].code			${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.3..value.ofType.CodeableConcept..coding.0.


BUG TRACE 07 Create Sofa Score (Invalid/Missing 'component' for Array value 4)
	[Documentation]		Belongs to TC 014! Remove separation when it's fixed!
	[Template]			create sofa score with ehr reference
    [Tags]              valueCodeableConcept    not-ready    not-ready_bug

   #invalid component 4 code coding system
	$.component[4].code.coding[0].system						http://foobar.de		422    	This element does not match any known slice defined in the profile ${sofa_score-url}                    								Observation.component

    #invalid component 4 code coding code
	$.component[4].code.coding[0].code							${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.4..code.coding.0.

    #invalid component 4 valueCodableConcept coding code
	$.component[4].valueCodeableConcept.coding[0].code			${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.4..value.ofType.CodeableConcept..coding.0.


BUG TRACE 08 Create Sofa Score (Invalid/Missing 'component' for Array value 5)
	[Documentation]		Belongs to TC 015! Remove separation when it's fixed!
	[Template]			create sofa score with ehr reference
    [Tags]              valueCodeableConcept    not-ready    not-ready_bug

   #invalid component 5 code coding system
	$.component[5].code.coding[0].system						http://foobar.de		422    	This element does not match any known slice defined in the profile ${sofa_score-url}                    								Observation.component

    #invalid component 5 code coding code
	$.component[5].code.coding[0].code							${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.5..code.coding.0.

    #invalid component 5 valueCodableConcept coding code
	$.component[5].valueCodeableConcept.coding[0].code			${randstring}			422    	This element does not match any known slice defined in the profile ${sofa_score-url}                        							Observation.component.5..value.ofType.CodeableConcept..coding.0.

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


create sofa score with ehr reference
	[Arguments]         ${json_path}        ${value}                 ${http_status_code}
	...					${error_message}    ${location}=${None}

						ehr.create new ehr                      000_ehr_status.json
	${payload}=    		generate payload from example json      ${json_path}                ${value}
						observation.POST /Observation           Sofa Score                  ${payload}
						observation.validate response - 422 (with error message)        ${http_status_code}
						...															        ${error_message}
						...															        ${location}


create sofa score w/o ehr reference    
	[Arguments]         ${json_path}        ${value}                ${http_status_code}
	...					${error_message}    ${location}=${None}

	${fake_ehr_ref}=	Evaluate    str(uuid.uuid4())    uuid
						Set Test Variable    ${subject_id}    ${fake_ehr_ref}
	${payload}=    		generate payload from example json              ${json_path}      ${value}
						observation.POST /Observation    Sofa Score    ${payload}
						observation.validate response - 422 (with error message)      ${http_status_code}
						...															      ${error_message}
						...															      ${location}


generate payload from example json
	[Documentation]		Generates actual request payload using example json as a starting point.
	[Arguments]			${json_path}    ${value}

	${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/create-sofa-score.json
                        Update Value To Json    ${payload}    $.subject.identifier.value    ${subject_id}
						Delete Object From Json    ${payload}    $.text

						# comment: delete field/object that has value 'missing' in test case table 
						Run Keyword And Return If   $value=="missing"
						...    	Run Keyword    Delete Object From Json    ${payload}    ${json_path}

						# comment: set value from data table in test case
						Update Value To Json            ${payload}    ${json_path}    ${value}
						Output Debug Info To Console    ${payload}

	[Return]			${payload}


create sofa score JSON
    [Arguments]         ${resourceType}    				${ID}    							${meta}    							${profile}
	...					${status}						${categoryavailable}    	    	${categorycodingavailable}    		${categorysystem}
	...					${categorycode}					${codeavailable}    				${codecodingavailable}    			${code0system}
	...					${code0code}					${code0display}						${subject}    						${subjectvalue}
	...					${effectivedatetime}			${componentavailabe}				${componentCodeavailable}			${componentCodeCodingavailable}
	...					${ComponentC0System}			${ComponentC0Code}					${componentvCCavailable}			${componentvCCCodingavailable}
	...					${ComponentvCC0System}			${ComponentvCC0Code}				${http_status_code}    				${error_message}
	...					${location}

                        prepare new request session    Prefer=return=representation

    &{resp}             Run Keywords
                        ...    ehr.create new ehr               000_ehr_status.json                             AND
                        ...    load JSON                        create-sofa-score.json				   	 		AND
                        ...    update Resource Type             ${resourceType}                                 AND
                        ...    update ID                        ${ID}                                           AND
                        ...    update Meta Profile              ${meta}                                         ${profile}                    	AND
                        ...    update Status                    ${status}                                       AND
                        ...    update Category                  ${categoryavailable}                            ${categorycodingavailable}     	${categorysystem}           		${categorycode}         	AND
                        ...    update Code 0                    ${codeavailable}                                ${codecodingavailable}         	${code0system}              		${code0code}            	${code0display}    	 	AND
                        ...    update Subject                   ${subject}                                      ${subjectvalue}                	AND
                        ...    update Effective Date Time       ${effectivedatetime}                            AND
						...    update Component					${componentavailabe}							${componentCodeavailable}		${componentCodeCodingavailable}		${ComponentC0System}		${ComponentC0Code}		${componentvCCavailable}    ${componentvCCCodingavailable}    ${ComponentvCC0System}    ${ComponentvCC0Code}    AND
                        ...    POST    ${BASE_URL}/Observation    body=${payload}                               AND
                        ...    Output Debug Info To Console                                                     AND
                        ...    observation.validate response - 422 (with error message)						${http_status_code}    			${error_message}    				${location}


generate payload from example json with data absentreason
	[Documentation]		Generates actual request payload using example json as a starting point.
	[Arguments]			${json_path}    ${value}

	${dict_componentdataabsentreason}			Create Dictionary	dataAbsentReason=${{ {"coding": [{"system": "http://terminology.hl7.org/CodeSystem/data-absent-reason", "code": "unknown", "display": "unknown"}], "text": "SOFA Score"} }}

	${payload}          Load JSON From File    		${DATA_SET_PATH_OBSERVATION}/create-sofa-score.json
                        Update Value To Json    	${payload}    $.subject.identifier.value    			${subject_id}
						Delete Object From Json    	${payload}    $.text
						Delete Object From Json    	${payload}    $.component[0].valueCodeableConcept
						Delete Object From Json    	${payload}    $.component[1].valueCodeableConcept
						Delete Object From Json    	${payload}    $.component[2].valueCodeableConcept
						Delete Object From Json    	${payload}    $.component[3].valueCodeableConcept
						Delete Object From Json    	${payload}    $.component[4].valueCodeableConcept
						Delete Object From Json    	${payload}    $.component[5].valueCodeableConcept
						Add Object To Json  		${payload}    $.component[0]								${dict_componentdataabsentreason}
						Add Object To Json  		${payload}    $.component[1]								${dict_componentdataabsentreason}
						Add Object To Json  		${payload}    $.component[2]								${dict_componentdataabsentreason}
						Add Object To Json  		${payload}    $.component[3]								${dict_componentdataabsentreason}
						Add Object To Json  		${payload}    $.component[4]								${dict_componentdataabsentreason}
						Add Object To Json  		${payload}    $.component[5]								${dict_componentdataabsentreason}

						# comment: delete field/object that has value 'missing' in test case table 
						Run Keyword And Return If   $value=="missing"
						...    	Run Keyword    Delete Object From Json    ${payload}    ${json_path}

						# comment: set value from data table in test case
						Update Value To Json            ${payload}    ${json_path}    ${value}
						Output Debug Info To Console    ${payload}

	[Return]			${payload}


create sofa score with ehr reference AND data absentreason
	[Arguments]         ${json_path}        ${value}                 ${http_status_code}
	...					${error_message}    ${location}=${None}

						ehr.create new ehr    000_ehr_status.json
	${payload}=    		generate payload from example json with data absentreason    ${json_path}    ${value}
						observation.POST /Observation    Sofa Score    ${payload}
						observation.validate response - 422 (with error message)     ${http_status_code}
						...															     ${error_message}
						...															     ${location}


create with DataAbsentReason
    [Arguments]         ${fhir_resource_name}    ${example_json}

	${dict_componentdataabsentreason}			Create Dictionary	dataAbsentReason=${{ {"coding": [{"system": "http://terminology.hl7.org/CodeSystem/data-absent-reason", "code": "unknown", "display": "unknown"}], "text": "SOFA Score"} }}

    ${payload}          Load JSON From File    		${DATA_SET_PATH_OBSERVATION}/${example_json}
                        Update Value To Json    	${payload}    $.subject.identifier.value    				${subject_id}
						Add Object To Json  		${payload}    $.component[0]								${dict_componentdataabsentreason}
						Add Object To Json  		${payload}    $.component[1]								${dict_componentdataabsentreason}
						Add Object To Json  		${payload}    $.component[2]								${dict_componentdataabsentreason}
						Add Object To Json  		${payload}    $.component[3]								${dict_componentdataabsentreason}
						Add Object To Json  		${payload}    $.component[4]								${dict_componentdataabsentreason}
						Add Object To Json  		${payload}    $.component[5]								${dict_componentdataabsentreason}
                        Output Debug Info To Console    ${payload}
                        POST /Observation    		${fhir_resource_name}    ${payload}