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
...															   Authorization=Basic bXl1c2VyOm15UGFzc3dvcmQ0MzI=
Documentation           *NOTE:* Use Regular Expressions to replace braces () as described here:
...                	    https://json-schema.org/understanding-json-schema/reference/regular_expressions.html#example \n\n
...						author: Peter Wohlfarth
Force Tags              create    observation-lab   invalid


*** Variables ***
${body_height-url}				https://www.medizininformatik-initiative.de/fhir/core/modul-labor/StructureDefinition/ObservationLab
${randstring}                   foobar
${randinteger}                  ${12345}
${identifiersystem}             https://www.charite.de/fhir/CodeSystem/lab-identifiers




*** Test Cases ***


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




005 Create Body Height (Invalid/Missing 'identifier')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-body-height.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID: ${subject_id}_ which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``identifier`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create body-height with ehr reference
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
	#$.identifier[0].value			${randstring}				 	422	   	if identifier.system is ''foobar'', then the identifier.value must be a full URI						Observation.identifier.0.




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

	#invalid coding
	$.category[0].coding    				missing					422    	Object must have some content																				Observation.category.0.
	$.category[0].coding    				${EMPTY}				422    	This property must be an Array, not a primitive property													Observation.category.0..coding
	$.category[0].coding					${{ [] }}				422    	Array cannot be empty - the property should not be present if it has no values								Observation.category.0..coding
	$.category[0].coding					${{ {} }}				422    	This property must be an Array, not an Object																Observation.category.0..coding
	$.category[0].coding					${{ [{}] }}				422    	Object must have some content																				Observation.category.0..coding

	#invalid code 0
	$.category[0].coding[0].code    		${EMPTY}    	    	422    	@value cannot be empty																						Observation.category.0..coding.0..code
	$.category[0].coding[0].code    		${randstring}	    	422    	This element does not match any known slice defined in the profile ${patient-ICU-url}						Observation.category.0..coding.0.
	$.category[0].coding[0].code    		${randinteger}	    	422    	Error parsing JSON: the primitive value must be a string													Observation.category.0..coding.0..code
    $.category[0].coding[0].code 			${{ [] }}				422    	This property must be an simple value, not an array                                							Observation.category.0..coding.0..code
	$.category[0].coding[0].code 			${{ {} }}				422    	This property must be an simple value, not an object														Observation.category.0..coding.0..code
	$.category[0].coding[0].code 			${{ [{}] }}				422    	This property must be an simple value, not an array															Observation.category.0..coding.0..code

	# invaild system 0
	$.category[0].coding[0].system    		${EMPTY}    	    	422    	@value cannot be empty																						Observation.category.0..coding.0..system
	$.category[0].coding[0].system    		${randstring}	    	422    	Coding.system must be an absolute reference, not a local reference											Observation.category.0..coding.0.
	$.category[0].coding[0].system    		${randinteger}	    	422    	Error parsing JSON: the primitive value must be a string													Observation.category.0..coding.0..system
	$.category[0].coding[0].system    		http://foobar.de      	422    	This element does not match any known slice defined in the profile ${patient-ICU-url}						Observation.category.0..coding.0.
    $.category[0].coding[0].system 			${{ [] }}				422    	This property must be an simple value, not an array                                							Observation.category.0..coding.0..system
	$.category[0].coding[0].system 			${{ {} }}				422    	This property must be an simple value, not an object														Observation.category.0..coding.0..system
	$.category[0].coding[0].system 			${{ [{}] }}				422    	This property must be an simple value, not an array															Observation.category.0..coding.0..system

code.coding.code
code.coding.system
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



vQ.value
vQ.unit
vQ.system
vQ.code
012 Create Body Height (Invalid/Missing 'valueQuantity')
	[Documentation]     1. *CREATE* new an EHR record\n\n 
	...                 2. *LOAD* _create-body-height.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...                 4. *UPDATE* values for attribute ``effectiveDateTime`` \n\n
    ...                 5. *POST* example JSON to observation endpoint\n\n
	...                 6. *VALIDATE* the response status \n\n
    ...                 7. *VALIDATE* outcome against diagnostic text & location
	[Template]			create body-height with ehr reference
    [Tags]              valueQuantity

	# FIELD/PATH								VALUE					HTTP	ERROR MESSAGE																								Location
	# 																	CODE

	# invalid/missing valueQuantity
	$.valueQuantity			  					missing			422    	.*If there is no component or hasMember element then either a value.x. or a data absent reason must be present
	$.valueQuantity			  					${None}			422    	This property must be an Object, not null
	$.valueQuantity			  					${{ {} }}		422    	Observation.value.x.:valueQuantity.value: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity			  					${{ {} }}		422    	Observation.value.x.:valueQuantity.unit: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity			  					${{ {} }}		422    	Observation.value.x.:valueQuantity.system: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity			  					${{ {} }}		422    	Observation.value.x.:valueQuantity.code: minimum required = 1, but only found 0 .from ${profile url}
	
	# missing parameters
	$.valueQuantity.value	  					missing			422    	Observation.value.x.:valueQuantity.value: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity.unit	  					missing			422    	Observation.value.x.:valueQuantity.unit: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity.system	  					missing			422    	Observation.value.x.:valueQuantity.system: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity.code	  					missing			422    	Observation.value.x.:valueQuantity.code: minimum required = 1, but only found 0 .from ${profile url}
	
	# invalid value
	$.valueQuantity.value	  					${EMPTY}		422    	Error parsing JSON: the primitive value must be a number
	$.valueQuantity.value	  					${None}			422    	This property must be an simple value, not null
	$.valueQuantity.value	  					113				422    	Error parsing JSON: the primitive value must be a number
	$.valueQuantity.value	  					${1001}			422    	.*value is not within interval, expected:0.0 <= 1001.0 <= 1000.0.*Bad Request.*
	$.valueQuantity.value	  					${1000.09}		422    	.*value is not within interval, expected:0.0 <= 1000.09 <= 1000.0.*Bad Request.*
	$.valueQuantity.value	  					${-1}			422    	.*value is not within interval, expected:0.0 <= -1.0 <= 1000.0.*Bad Request.*
	$.valueQuantity.value	  					1000,7			422    	The value '1000,7' is not a valid decimal    Observation.value.ofType.Quantity..value
	$.valueQuantity.value	  					foobar			422    	Error parsing JSON: the primitive value must be a number
	
	# invalid unit
	$.valueQuantity.unit	  					${EMPTY}		422    	@value cannot be empty    Observation.value.ofType.Quantity..unit
	$.valueQuantity.unit	  					${None}			422    	Observation.value.x.:valueQuantity.unit: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity.unit	  					${123}			422    	Error parsing JSON: the primitive value must be a string
	
	# invalid system
	$.valueQuantity.system	  					${EMPTY}		422    	@value cannot be empty    Observation.value.ofType.Quantity..system
	$.valueQuantity.system	  					${None}			422    	Observation.value.x.:valueQuantity.system: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity.system	  					foobar			422    	Value is 'foobar' but must be 'http://unitsofmeasure.org'
	$.valueQuantity.system	  					${123}			422    	Error parsing JSON: the primitive value must be a string
	
	#invalid code
	$.valueQuantity.code	  					${EMPTY}		422    	@value cannot be empty    Observation.value.ofType.Quantity..code
	$.valueQuantity.code	  					${None}			422    	Observation.value.x.:valueQuantity.code: minimum required = 1, but only found 0 .from ${profile url}
	$.valueQuantity.code	  					${123}			422    	Error parsing JSON: the primitive value must be a string
	$.valueQuantity.code	  					foobar			422    	.*No matching units for:foobar, expected units:cm.*Bad Request.*
