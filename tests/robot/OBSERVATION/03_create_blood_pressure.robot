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

Force Tags              create



*** Variables ***




*** Test Cases ***
000 Create Blood Pressuere Fails Due To Missing EHR Reference
	[Documentation]    Trigger observation endpoint w/o creating an EHR first
	[Tags]				

	observation.create blood pressure without ehr reference    observation-bloodpressure-example.json
	observation.validate response - 404 (with error message)    EHR for patient 07f602e0-579e-4fe3-95af-381728bf0d49 doesn't exists


001 Create Blood Pressure Fails Due To Invalid/Missing 'resourceType'
	[Documentation]     TODO
	...					NOTE: use Regular Expressions to replace braces () as described here:
	...          		https://json-schema.org/understanding-json-schema/reference/regular_expressions.html#example
	[Template]			fail to create blood pressure
    [Tags]              

	# FIELD/PATH		VALUE			ISSUE	ERROR MESSAGE
	# 									INDEX
	$.resourceType		missing			0		Unable to find resourceType property
	$.resourceType		foobar			0		This does not appear to be a FHIR resource .unknown name 'foobar'.
	$.resourceType		${EMPTY}		0		This does not appear to be a FHIR resource .unknown name ''.
    $.resourceType		${123}			0		This does not appear to be a FHIR resource .unknown name '123'.


002 Create Blood Pressure Fails Due To Invalid/Missing 'meta'
	[Documentation]     TODO
	[Template]			fail to create blood pressure
    [Tags]              

	# FIELD/PATH		VALUE						ISSUE	ERROR MESSAGE
	# 												INDEX
	$.meta				missing						0		Default profile is not supported for Observation. One of the following profiles is expected: .http://...
	$.meta.profile		missing						0		Object must have some content
	$.meta.profile      ${{["invalid_url"]}}		0   	Canonical URLs must be absolute URLs if they are not fragment references .invalid_url.
	$.meta.profile      ${{["http://wrong.url"]}}	0    	Profile reference 'http://wrong.url' could not be resolved, so has not been checked
	$.meta.profile		${EMPTY}					0		This property must be an Array, not a a primitive property

	# comment: the next one sets the value to an empty list/array []
	$.meta.profile		${{[]}}						0		Default profile is not supported for Observation. One of the following profiles is expected: .http://...
	
	# comment: the next one sets value to an empty object {}
	$.meta.profile		${{{}}}						0		This property must be an Array, not a an object


003 Create Blood Pressure Fails Due To Invalid/Missing 'code'
	[Documentation]     TODO
	[Template]			fail to create blood pressure
    [Tags]              

	# FIELD/PATH		VALUE			ISSUE	ERROR MESSAGE    LOCATION
	# 									INDEX
	$.code				missing			0		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.code': minimum required = 1, but only found 0
	

004 Create Blood Pressure Fails Due To Invalid/Missing 'category:VSCat'
	[Documentation]     TODO
	[Template]			fail to create blood pressure
    [Tags]              

	# FIELD/PATH		VALUE			ISSUE	ERROR MESSAGE    LOCATION
	# 									INDEX
	$.category			missing			0		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.category': minimum required = 1, but only found 0
	$.category			${{[]}}			0		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.category': minimum required = 1, but only found 0
	$.category			${{{}}}			0		This property must be an Array, not an Object
	$.category			${{[{}]}}		0		Object must have some content

	# FIELD/PATH			VALUE		INDEX	ERROR MESSAGE
	$.category[0].coding    missing		0		Object must have some content
	$.category[0].coding    ${EMPTY}	0		This property must be an Array, not a primitive property

	# FIELD/PATH						VALUE		INDEX	ERROR MESSAGE
	$.category[0].coding[0].code    	missing    	2    	This element does not match any known slice defined in the profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure
	$.category[0].coding[0].code    	${EMPTY}    2    	@value cannot be empty
	$.category[0].coding[0].code    	foobar    	2    	This element does not match any known slice defined in the profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure
	...														Observation.component[0].code.coding[2]

	$.category[0].coding[0].system    	missing    	2    	A code with no system has no defined meaning. A system should be provided
	...														Observation.category[0].coding[0]
	$.category[0].coding[0].system    	${EMPTY}    3    	@value cannot be empty
	...														Observation.category[0].coding[0].system
	$.category[0].coding[0].system    	foobar    	2    	Coding.system must be an absolute reference, not a local reference
	...														Observation.category[0].coding[0]
	$.category[0].coding[0].system    	http://foobar.de    	0    	This element does not match any known slice defined in the profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure
	...														Observation.category[0]


005 Create Blood Pressure Fails Due To Invalid/Missing 'component:SystolicBP'
	[Documentation]     TODO
	[Template]			fail to create blood pressure
    [Tags]              

	# FIELD/PATH		VALUE		ISSUE	ERROR MESSAGE    LOCATION
	# 								INDEX
	$.component			missing		1		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component': minimum required = 2, but only found 0
	$.component[0]		missing		0		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component': minimum required = 2, but only found 1
	$.component[1]		missing		0		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component': minimum required = 2, but only found 1
	$.component[1]		missing		1		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.DiastolicBP.': minimum required = 1, but only found 0
	
	# FIELD/PATH							VALUE		ISSUE	ERROR MESSAGE    LOCATION
	$.component[0].code  					missing		2		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.0..code': minimum required = 1, but only found 0
	$.component[0].code.coding[0].system    missing    	2    	A code with no system has no defined meaning. A system should be provided
	...															Observation.component[0].code.coding[0]
	$.component[0].code.coding[0].system    ${EMPTY}    3    	@value cannot be empty
	...															Observation.component[0].code.coding[0].system
	$.component[0].code.coding[0].system    foobar    	2    	Coding.system must be an absolute reference, not a local reference
	...															Observation.component[0].code.coding[0]
	$.component[0].code.coding[0].system    http://f.de    1    Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.SystolicBP.': minimum required = 1, but only found 0
	
	
	$.component[0].code.coding[0].code    	missing    	1    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.SystolicBP.': minimum required = 1, but only found 0
	$.component[0].code.coding[0].code    	${EMPTY}   	2    	@value cannot be empty
	...															Observation.component[0].code.coding[0].code
	$.component[0].code.coding[0].code    	${123}		0    	Error parsing JSON: the primitive value must be a string
	...															Observation.component[0].code.coding[0].code
	$.component[0].code.coding[0].code    	foobar    	1    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.SystolicBP.': minimum required = 1, but only found 0
	

	$.component[0].valueQuantity			missing		0		vs-3: If there is no a value a data absent reason must be present .value.exists.. or dataAbsentReason.exists...
	...															Observation.component[0]
	
	$.component[0].valueQuantity			${EMPTY}	0		This property must be an Object, not a primitive property
	...															Observation.component[0].value[x]
	
	$.component[0].valueQuantity			${{{}}}		0		Object must have some content
	...															Observation.component[0].value[x]
	
	$.component[0].valueQuantity.value		missing		1		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.0..value.ofType.Quantity..value': minimum required = 1, but only found 0
	...															Observation.component[0].value.ofType(Quantity)
	
	$.component[0].valueQuantity.value		${EMPTY}	0		Error parsing JSON: the primitive value must be a number
	...															Observation.component[0].value[x].value
	
	$.component[0].valueQuantity.value		${EMPTY}	2		@value cannot be empty
	...															Observation.component[0].value.ofType(Quantity).value
	
	$.component[0].valueQuantity.value		${None}		0		This property must be an simple value, not null
	...															Observation.component[0].value[x].value
	
	$.component[0].valueQuantity.value		107			0		Error parsing JSON: the primitive value must be a number
	...															Observation.component[0].value[x].value



	# TODO: REPORT ISSUE ???
	$.component[0].valueQuantity.value		${1000}			0		There was a problem saving the compositionWrong Status code. Expected: .200, 201, 204.. Got: 400. Error message.*value is not within interval, expected:0.0 <= 1000.0 < 1000.0.*Bad Request.*
	$.component[0].valueQuantity.value		${-1}			0		There was a problem saving the compositionWrong Status code. Expected: .200, 201, 204.. Got: 400. Error message.*value is not within interval, expected:0.0 <= -1.0 < 1000.0.*Bad Request.*
	


	$.component[0].valueQuantity.value		foobar		2		The value 'foobar' is not a valid decimal
	...															Observation.component[0].value.ofType(Quantity).value
	
	$.component[0].valueQuantity.unit		missing		1		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.0..value.ofType.Quantity..unit': minimum required = 1, but only found 0
	...															Observation.component[0].value.ofType(Quantity)
	
	$.component[0].valueQuantity.unit		${EMPTY}	1		@value cannot be empty
	...															Observation.component[0].value.ofType(Quantity).unit
	
	$.component[0].valueQuantity.unit		${123}		0		Error parsing JSON: the primitive value must be a string
	...															Observation.component[0].value[x].unit
	
	$.component[0].valueQuantity.system		missing		3		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.0..value.ofType.Quantity..system': minimum required = 1, but only found 0
	...															Observation.component[0].value.ofType(Quantity)
	
	$.component[0].valueQuantity.system		${EMPTY}	2		@value cannot be empty
	...															Observation.component[0].value.ofType(Quantity).system

	$.component[0].valueQuantity.system		foobar		2		Value is 'foobar' but must be 'http://unitsofmeasure.org'
	...															Observation.component[0].value.ofType(Quantity).system

	$.component[0].valueQuantity.system		${123}		0		Error parsing JSON: the primitive value must be a string
	...															Observation.component[0].value[x].system

	$.component[0].valueQuantity.code		missing		1		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.0..value.ofType.Quantity..code': minimum required = 1, but only found 0
	...															Observation.component[0].value.ofType(Quantity)

	$.component[0].valueQuantity.code		${EMPTY}	1		@value cannot be empty
	...															Observation.component[0].value.ofType(Quantity).code

	$.component[0].valueQuantity.code		${123}		0		Error parsing JSON: the primitive value must be a string
	...															Observation.component[0].value[x].code

	$.component[0].valueQuantity.code		foobar		1		Value is 'foobar' but must be 'mm.Hg.'
	...															Observation.component[0].value.ofType(Quantity).code


006 Create Blood Pressure Fails Due To Invalid/Missing 'component:DiastolicBP'
	[Documentation]     TODO
	[Template]			fail to create blood pressure
    [Tags]              yyy

	# FIELD/PATH							VALUE		ISSUE	ERROR MESSAGE    LOCATION
	$.component[1].code  					missing		1		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.DiastolicBP.': minimum required = 1, but only found 0
	$.component[1].code.coding[0].system    missing    	3    	A code with no system has no defined meaning. A system should be provided
	...															Observation.component[1].code.coding[0]
	
	$.component[1].code.coding[0].system    ${EMPTY}    4    	@value cannot be empty
	...															Observation.component[1].code.coding[0].system
	
	$.component[1].code.coding[0].system    foobar    	3    	Coding.system must be an absolute reference, not a local reference
	...															Observation.component[1].code.coding[0]
	
	$.component[1].code.coding[0].system    http://f.de    1    Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.DiastolicBP.': minimum required = 1, but only found 0
	$.component[1].code.coding[0].code    	missing    	1    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.DiastolicBP.': minimum required = 1, but only found 0
	$.component[1].code.coding[0].code    	${EMPTY}   	3    	@value cannot be empty
	...															Observation.component[1].code.coding[0].code
	
	$.component[1].code.coding[0].code    	${123}		0    	Error parsing JSON: the primitive value must be a string
	...															Observation.component[1].code.coding[0].code
	
	$.component[1].code.coding[0].code    	foobar    	1    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.DiastolicBP.': minimum required = 1, but only found 0

	$.component[1].valueQuantity			missing		1		vs-3: If there is no a value a data absent reason must be present .value.exists.. or dataAbsentReason.exists...
	...															Observation.component[1]
	
	$.component[1].valueQuantity			${EMPTY}	0		This property must be an Object, not a primitive property
	...															Observation.component[1].value[x]
	
	$.component[1].valueQuantity			${{{}}}		0		Object must have some content
	...															Observation.component[1].value[x]
	
	$.component[1].valueQuantity.value		missing		1		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.1..value.ofType.Quantity..value': minimum required = 1, but only found 0
	...															Observation.component[1].value.ofType(Quantity)
	
	$.component[1].valueQuantity.value		${EMPTY}	0		Error parsing JSON: the primitive value must be a number
	...															Observation.component[1].value[x].value
	
	$.component[1].valueQuantity.value		${EMPTY}	2		@value cannot be empty
	...															Observation.component[1].value.ofType(Quantity).value
	
	$.component[1].valueQuantity.value		${None}		0		This property must be an simple value, not null
	...															Observation.component[1].value[x].value
	
	$.component[1].valueQuantity.value		107			0		Error parsing JSON: the primitive value must be a number
	...															Observation.component[1].value[x].value



	$.component[1].valueQuantity.value		${1000}			0		There was a problem saving the compositionWrong Status code. Expected: .200, 201, 204.. Got: 400. Error message.*value is not within interval, expected:0.0 <= 1000.0 < 1000.0.*Bad Request.*
	$.component[1].valueQuantity.value		${-1}			0		There was a problem saving the compositionWrong Status code. Expected: .200, 201, 204.. Got: 400. Error message.*value is not within interval, expected:0.0 <= -1.0 < 1000.0.*Bad Request.*
	


	$.component[1].valueQuantity.value		foobar		2		The value 'foobar' is not a valid decimal
	...															Observation.component[1].value.ofType(Quantity).value
	
	$.component[1].valueQuantity.unit		missing		1		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.1..value.ofType.Quantity..unit': minimum required = 1, but only found 0
	...															Observation.component[1].value.ofType(Quantity)
	
	$.component[1].valueQuantity.unit		${EMPTY}	1		@value cannot be empty
	...															Observation.component[1].value.ofType(Quantity).unit
	
	$.component[1].valueQuantity.unit		${123}		0		Error parsing JSON: the primitive value must be a string
	...															Observation.component[1].value[x].unit
	
	$.component[1].valueQuantity.system		missing		3		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.1..value.ofType.Quantity..system': minimum required = 1, but only found 0
	...															Observation.component[1].value.ofType(Quantity)
	
	$.component[1].valueQuantity.system		${EMPTY}	2		@value cannot be empty
	...															Observation.component[1].value.ofType(Quantity).system

	$.component[1].valueQuantity.system		foobar		2		Value is 'foobar' but must be 'http://unitsofmeasure.org'
	...															Observation.component[1].value.ofType(Quantity).system

	$.component[1].valueQuantity.system		${123}		0		Error parsing JSON: the primitive value must be a string
	...															Observation.component[1].value[x].system

	$.component[1].valueQuantity.code		missing		1		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.1..value.ofType.Quantity..code': minimum required = 1, but only found 0
	...															Observation.component[1].value.ofType(Quantity)

	$.component[1].valueQuantity.code		${EMPTY}	1		@value cannot be empty
	...															Observation.component[1].value.ofType(Quantity).code

	$.component[1].valueQuantity.code		${123}		0		Error parsing JSON: the primitive value must be a string
	...															Observation.component[1].value[x].code

	$.component[1].valueQuantity.code		foobar		1		Value is 'foobar' but must be 'mm.Hg.'
	...															Observation.component[1].value.ofType(Quantity).code






*** Keywords ***
fail to create blood pressure
    [Arguments]         ${json_path}  ${value}  ${issue_index}  ${error_message}  ${location}=${None}

	ehr.create new ehr    000_ehr_status.json
	${payload}=			generate payload from example json    ${json_path}  ${value}
	# create blood pressure    ${payload}
	observation.POST /Observation    Blood Pressure    ${payload}
	observation.validate response - 422 (with error message)    ${issue_index}    ${error_message}
	...															${location}


generate payload from example json
	[Arguments]			${json_path}  ${value}

	${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/observation-bloodpressure-example.json
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}
						Delete Object From Json    ${payload}    $.text

	# comment: delete field/object
	Run Keyword And Return If   $value=="missing"
	...    Run Keyword    Delete Object From Json    ${payload}    ${json_path}    #AND
	# ...	   Set Test Variable    ${payload}    ${payload}
		   Output Debug Info To Console    ${payload}

	# comment: set value from table
	Update Value To Json    ${payload}    ${json_path}    ${value}
	# Set Test Variable    ${payload}    ${payload}

	[Return]			${payload}

# create blood pressure
#     [Arguments]         ${fhir_resource}

#     &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
#                         Output Debug Info To Console

# 	observation.po