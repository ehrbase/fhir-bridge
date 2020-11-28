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

Force Tags              create    bloodpressure



*** Variables ***




*** Test Cases ***
000 Create Blood Pressuere Fails Due To Invalid/Missing EHR Reference
	[Documentation]    	Trigger observation endpoint w/o creating an EHR first
	[Template]			create blood pressure w/o ehr reference
	[Tags]				

	# FIELD/PATH					VALUE			ISSUE	HTTP	ERROR MESSAGE
	# 												INDEX	CODE
	$.subject.identifier.value		missing			0		422		Subject identifier is required
	$.subject.identifier.value		foobar			0		422		EhrId not found for subject 'foobar'
	$.subject.identifier.value		${EMPTY}		0		422		Subject identifier is required
	$.subject.identifier.value		${{ [] }}		0		422		Subject identifier is required
	$.subject.identifier.value		${{ {} }}		0		422		Subject identifier is required
	$.subject.identifier.value		${{ 123 }}		0		422		EhrId not found for subject '123'
	$.subject.identifier			missing			0		422		.* Element 'Observation.subject': minimum required = 1, but only found 0
    $.subject.identifier			${EMPTY}		0		422		.* Element 'Observation.subject': minimum required = 1, but only found 0
    $.subject.identifier			${{ [] }}		0		422		.* Element 'Observation.subject': minimum required = 1, but only found 0
    $.subject.identifier			${{ {} }}		0		422		.* Element 'Observation.subject': minimum required = 1, but only found 0
    $.subject.identifier			${{ 123 }}		0		422		.* Element 'Observation.subject': minimum required = 1, but only found 0
    $.subject						missing			0		422		.* Element 'Observation.subject': minimum required = 1, but only found 0
	$.subject						${EMPTY}		0		422		.* Element 'Observation.subject': minimum required = 1, but only found 0
    $.subject						${{ [] }}		0		422		.* Element 'Observation.subject': minimum required = 1, but only found 0
    $.subject						${{ {} }}		0		422		.* Element 'Observation.subject': minimum required = 1, but only found 0
    $.subject						${{ 123 }}		0		422		.* Element 'Observation.subject': minimum required = 1, but only found 0

	# comment: random uuid																			 regex for uuid
	$.subject.identifier.value    ${{str(uuid.uuid4())}}    0    422    EhrId not found for subject '[0-9a-f]{32}|[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}'
	

001 Create Blood Pressure Fails Due To Invalid/Missing 'resourceType'
	[Documentation]     TODO
	...					NOTE: use Regular Expressions to replace braces () as described here:
	...          		https://json-schema.org/understanding-json-schema/reference/regular_expressions.html#example
	[Template]			create blood pressure with ehr reference
    [Tags]          	

	# FIELD/PATH		VALUE			ISSUE	HTTP	ERROR MESSAGE
	# 									INDEX	CODE	
	$.resourceType		missing			0		400		.*Invalid JSON content detected, missing required element: 'resourceType'
	$.resourceType		foobar			0		400		.*Incorrect resource type found, expected .*Observation.* but found .*foobar.*
	$.resourceType		${EMPTY}		0		400		.*Invalid JSON content detected, missing required element: 'resourceType'
    $.resourceType		${123}			0		400		.*Invalid JSON content detected, missing required element: 'resourceType'


002 Create Blood Pressure Fails Due To Invalid/Missing 'meta'
	[Documentation]     TODO
	[Template]			create blood pressure with ehr reference
    [Tags]             	

	# FIELD/PATH		VALUE						ISSUE	ERROR MESSAGE
	# 												INDEX
	# $.meta			missing						0		422    Default profile is not supported for Observation. One of the following profiles is expected: .http://...
	# $.meta.profile	missing						0		422    Object must have some content
	# $.meta.profile    ${{ ["invalid_url"] }}		0   	422    Canonical URLs must be absolute URLs if they are not fragment references .invalid_url.
	# $.meta.profile    ${{ ["http://wrong.url"] }}	0    	422    Profile reference 'http://wrong.url' could not be resolved, so has not been checked
	# $.meta.profile	${EMPTY}					0		422    This property must be an Array, not a a primitive property
	
	
	$.meta				missing						0		422    Default profile is not supported
	$.meta.profile		missing						0		422    Default profile is not supported
	$.meta.profile      ${{ ["invalid_url"] }}		0   	422    Profile 'invalid_url' not supported for Observation
	$.meta.profile      ${{ ["http://wrong.url"] }}	0    	422    Profile 'http://wrong.url' not supported for Observation
	$.meta.profile		${EMPTY}					0		422    Profile 'null' not supported for Observation

	# comment: the next one sets the value to an empty list/array []
	$.meta.profile		${{ [] }}					0		422    Default profile is not supported
	
	# comment: the next one sets value to an empty object {}
	# $.meta.profile	${{ {} }}					0		422    This property must be an Array, not a an object
	$.meta.profile		${{ {} }}					0		422    Profile 'null' not supported for Observation


003 Create Blood Pressure Fails Due To Invalid/Missing 'code'
	[Documentation]     TODO
	[Template]			create blood pressure with ehr reference
    [Tags]              

	# FIELD/PATH		VALUE			ISSUE	HTTP	ERROR MESSAGE
	# 									INDEX	CODE
	$.code				missing			0		422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.code': minimum required = 1, but only found 0
	

004 Create Blood Pressure Fails Due To Invalid/Missing 'category:VSCat'
	[Documentation]     TODO
	[Template]			create blood pressure with ehr reference
    [Tags]              

	# FIELD/PATH						VALUE			ISSUE	HTTP	ERROR MESSAGE    ... LOCATION
	# 													INDEX	CODE
	$.category							missing			0		422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.category': minimum required = 1, but only found 0
	$.category							${{ [] }}		0		422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.category': minimum required = 1, but only found 0
	
	# $.category						${{ {} }}		0		422    	This property must be an Array, not an Object
	$.category							${{ {} }}		0		422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.category': minimum required = 1, but only found 0

	# $.category						${{ [{}] }}		0		422    	Object must have some content
	$.category							${{ [{}] }}		0		422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.category': minimum required = 1

	$.category[0].coding    			missing			0		422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.category': minimum required = 1
	$.category[0].coding    			${EMPTY}		0		422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.category': minimum required = 1

	$.category[0].coding[0].code    	missing    		0    	422    	This element does not match any known slice defined in the profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure
	
	# $.category[0].coding[0].code    	${EMPTY}    	2    	422    	@value cannot be empty
	$.category[0].coding[0].code    	${EMPTY}    	0    	422    	This element does not match any known slice defined in the profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure
	$.category[0].coding[0].code    	foobar    		0    	422    	This element does not match any known slice defined in the profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure
	...																	Observation.category[0]

	$.category[0].coding[0].system    	missing    		2    	422    	A code with no system has no defined meaning. A system should be provided
	...																	Observation.category[0].coding[0]


	# $.category[0].coding[0].system    ${EMPTY}    	3    	422    	@value cannot be empty
	# ...																Observation.category[0].coding[0].system
	$.category[0].coding[0].system    	${EMPTY}   		0    	422    	This element does not match any known slice defined in the profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure
	...																	Observation.category[0]
	
	$.category[0].coding[0].system    	foobar    		2    	422    	Coding.system must be an absolute reference, not a local reference
	...																	Observation.category[0].coding[0]
	
	$.category[0].coding[0].system    	http://foobar.de  0    	422    	This element does not match any known slice defined in the profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure
	...																	Observation.category[0]


005 Create Blood Pressure Fails Due To Invalid/Missing 'component:SystolicBP'
	[Documentation]     TODO
	[Template]			create blood pressure with ehr reference
    [Tags]              

	# # FIELD/PATH		VALUE		ISSUE	ERROR MESSAGE    LOCATION
	# # 								INDEX
	# $.component			missing		1		422    Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component': minimum required = 2, but only found 0
	# $.component[0]		missing		0		422    Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component': minimum required = 2, but only found 1
	# $.component[1]		missing		0		422    Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component': minimum required = 2, but only found 1
	# $.component[1]		missing		1		422    Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.DiastolicBP.': minimum required = 1, but only found 0
	
	# # FIELD/PATH							VALUE		ISSUE	HTTP	ERROR MESSAGE    LOCATION
	# $.component[0].code  					missing		2		422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.0..code': minimum required = 1, but only found 0
	# $.component[0].code.coding[0].system    missing    	2    	422    	A code with no system has no defined meaning. A system should be provided
	# ...																   	Observation.component[0].code.coding[0]
	
	# # $.component[0].code.coding[0].system    ${EMPTY}    3    	422    	@value cannot be empty
	# # ...																Observation.component[0].code.coding[0].system
	# $.component[0].code.coding[0].system    ${EMPTY}    2    	422    	A code with no system has no defined meaning. A system should be provided
	# ...																	Observation.component[0].code.coding[0]
	
	# $.component[0].code.coding[0].system    foobar    	2    	422    	Coding.system must be an absolute reference, not a local reference
	# ...																	Observation.component[0].code.coding[0]
	
	# $.component[0].code.coding[0].system    http://f.de    1    422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.SystolicBP.': minimum required = 1, but only found 0
	
	
	# $.component[0].code.coding[0].code    	missing    	1    	422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.SystolicBP.': minimum required = 1, but only found 0
	# $.component[0].code.coding[0].code    	${EMPTY}   	1    	422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.SystolicBP.': minimum required = 1, but only found 0
	
	# # $.component[0].code.coding[0].code    ${123}		1    	422    Error parsing JSON: the primitive value must be a string
	# # ...																Observation.component[0].code.coding[0].code
	# $.component[0].code.coding[0].code    	${123}		1    	422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.SystolicBP.': minimum required = 1, but only found 0
	# ...																	Observation
	
	# $.component[0].code.coding[0].code    	foobar    	1    	422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.SystolicBP.': minimum required = 1, but only found 0
	

	# $.component[0].valueQuantity			missing		0		422    	vs-3: If there is no a value a data absent reason must be present .value.exists.. or dataAbsentReason.exists...
	# ...																	Observation.component[0]
	
	# $.component[0].valueQuantity			${EMPTY}	0		422    	vs-3: If there is no a value a data absent reason must be present .value.exists.. or dataAbsentReason.exists...
	# ...																	Observation.component[0]
	
	# $.component[0].valueQuantity			${{{}}}		0		422    	vs-3: If there is no a value a data absent reason must be present .value.exists.. or dataAbsentReason.exists...
	# ...																	Observation.component[0]
	
	# $.component[0].valueQuantity.value		missing		1		422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.0..value.ofType.Quantity..value': minimum required = 1, but only found 0
	# ...																	Observation.component[0].value.ofType(Quantity)
	
	# $.component[0].valueQuantity.value		${EMPTY}	1		422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.0..value.ofType.Quantity..value': minimum required = 1, but only found 0
	# ...																	Observation.component[0].value.ofType(Quantity)
	
	# $.component[0].valueQuantity.value		${EMPTY}	1		422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.0..value.ofType.Quantity..value': minimum required = 1, but only found 0
	# ...																	Observation.component[0].value.ofType(Quantity)
	
	# $.component[0].valueQuantity.value		${None}		1		422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.0..value.ofType.Quantity..value': minimum required = 1, but only found 0
	# ...																	Observation.component[0].value.ofType(Quantity)
	
	# # ISSUTE REPORTED --> https://github.com/ehrbase/fhir-bridge-poc/issues/35
	# $.component[0].valueQuantity.value		107			0		422    	Error parsing JSON: the primitive value must be a number
	# ...																	Observation.component[0].value[x].value



	# # REPORTED ISSUE --> https://github.com/ehrbase/fhir-bridge-poc/issues/36
	# $.component[0].valueQuantity.value		${1000}			0		422    There was a problem saving the compositionWrong Status code. Expected: .200, 201, 204.. Got: 400. Error message.*value is not within interval, expected:0.0 <= 1000.0 < 1000.0.*Bad Request.*
	# $.component[0].valueQuantity.value		${-1}			0		422    There was a problem saving the compositionWrong Status code. Expected: .200, 201, 204.. Got: 400. Error message.*value is not within interval, expected:0.0 <= -1.0 < 1000.0.*Bad Request.*
	


	$.component[0].valueQuantity.value		foobar		0		400    	.*Invalid attribute value .*foobar.* Character f is neither a decimal digit number.*
	# ...																	Observation.component[0].value.ofType(Quantity).value
	
	$.component[0].valueQuantity.unit		missing		1		422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.0..value.ofType.Quantity..unit': minimum required = 1, but only found 0
	...																	Observation.component[0].value.ofType(Quantity)
	
	$.component[0].valueQuantity.unit		${EMPTY}	1		422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.0..value.ofType.Quantity..unit': minimum required = 1, but only found 0
	...																	Observation.component[0].value.ofType(Quantity)
	



	# TODO: CREATE ISSUE???  gibt aktuell ein 201 zurueck
	# $.component[0].valueQuantity.unit		${123}		0		422    	Error parsing JSON: the primitive value must be a string
	# ...																	Observation.component[0].value[x].unit
	


	$.component[0].valueQuantity.system		missing		3		422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.0..value.ofType.Quantity..system': minimum required = 1, but only found 0
	...																	Observation.component[0].value.ofType(Quantity)
	

	# $.component[0].valueQuantity.system		${EMPTY}	2		422    	@value cannot be empty
	# ...																	Observation.component[0].value.ofType(Quantity).system
	$.component[0].valueQuantity.system		${EMPTY}	3		422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.0..value.ofType.Quantity..system': minimum required = 1, but only found 0
	...																	Observation.component[0].value.ofType(Quantity)



	$.component[0].valueQuantity.system		foobar		2		422    	Value is 'foobar' but must be 'http://unitsofmeasure.org'
	...																	Observation.component[0].value.ofType(Quantity).system

	
	
	# $.component[0].valueQuantity.system		${123}		0		422    	Error parsing JSON: the primitive value must be a string
	# ...																	Observation.component[0].value[x].system
	$.component[0].valueQuantity.system		${123}		2		422    	Value is '123' but must be 'http://unitsofmeasure.org'
	...																	Observation.component[0].value.ofType(Quantity).system

	
	
	$.component[0].valueQuantity.code		missing		1		422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.0..value.ofType.Quantity..code': minimum required = 1, but only found 0
	...																	Observation.component[0].value.ofType(Quantity)

	
	
	# $.component[0].valueQuantity.code		${EMPTY}	1		422    	@value cannot be empty
	# ...																	Observation.component[0].value.ofType(Quantity).code
	$.component[0].valueQuantity.code		${EMPTY}	1		422    	Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.0..value.ofType.Quantity..code': minimum required = 1, but only found 0
	...																	Observation.component[0].value.ofType(Quantity)

	
	
	
	# $.component[0].valueQuantity.code		${123}		0		422    	Error parsing JSON: the primitive value must be a string
	# ...																	Observation.component[0].value[x].code
	$.component[0].valueQuantity.code		${123}		1		422    	Value is '123' but must be 'mm.Hg.'
	...																	Observation.component[0].value.ofType(Quantity).code

	
	
	$.component[0].valueQuantity.code		foobar		1		422    	Value is 'foobar' but must be 'mm.Hg.'
	...																	Observation.component[0].value.ofType(Quantity).code


006 Create Blood Pressure Fails Due To Invalid/Missing 'component:DiastolicBP'
	[Documentation]     TODO
	[Template]			create blood pressure with ehr reference
    [Tags]              not-ready    xxx

	# FIELD/PATH							VALUE		ISSUE	HTTP	ERROR MESSAGE    LOCATION
	$.component[1].code  					missing		1		422		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.DiastolicBP.': minimum required = 1, but only found 0
	$.component[1].code.coding[0].system    missing    	3    	422		A code with no system has no defined meaning. A system should be provided
	...																	Observation.component[1].code.coding[0]
	
	$.component[1].code.coding[0].system    ${EMPTY}    4    	422		@value cannot be empty
	...																	Observation.component[1].code.coding[0].system
	
	$.component[1].code.coding[0].system    foobar    	3    	422		Coding.system must be an absolute reference, not a local reference
	...																	Observation.component[1].code.coding[0]
	
	$.component[1].code.coding[0].system    http://f.de  1   	422		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.DiastolicBP.': minimum required = 1, but only found 0
	$.component[1].code.coding[0].code    	missing    	1    	422		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.DiastolicBP.': minimum required = 1, but only found 0
	$.component[1].code.coding[0].code    	${EMPTY}   	3    	422		@value cannot be empty
	...																	Observation.component[1].code.coding[0].code
	
	$.component[1].code.coding[0].code    	${123}		0    	422		Error parsing JSON: the primitive value must be a string
	...																	Observation.component[1].code.coding[0].code
	
	$.component[1].code.coding[0].code    	foobar    	1    	422		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.DiastolicBP.': minimum required = 1, but only found 0

	$.component[1].valueQuantity			missing		1		422		vs-3: If there is no a value a data absent reason must be present .value.exists.. or dataAbsentReason.exists...
	...																	Observation.component[1]
	
	$.component[1].valueQuantity			${EMPTY}	0		422		This property must be an Object, not a primitive property
	...																	Observation.component[1].value[x]
	
	$.component[1].valueQuantity			${{{}}}		0		422		Object must have some content
	...																	Observation.component[1].value[x]
	
	$.component[1].valueQuantity.value		missing		1		422		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.1..value.ofType.Quantity..value': minimum required = 1, but only found 0
	...																	Observation.component[1].value.ofType(Quantity)
	
	$.component[1].valueQuantity.value		${EMPTY}	0		422		Error parsing JSON: the primitive value must be a number
	...																	Observation.component[1].value[x].value
	
	$.component[1].valueQuantity.value		${EMPTY}	2		422		@value cannot be empty
	...																	Observation.component[1].value.ofType(Quantity).value
	
	$.component[1].valueQuantity.value		${None}		0		422		This property must be an simple value, not null
	...																	Observation.component[1].value[x].value
	
	$.component[1].valueQuantity.value		107			0		422		Error parsing JSON: the primitive value must be a number
	...																	Observation.component[1].value[x].value



	$.component[1].valueQuantity.value		${1000}		0		422		There was a problem saving the compositionWrong Status code. Expected: .200, 201, 204.. Got: 400. Error message.*value is not within interval, expected:0.0 <= 1000.0 < 1000.0.*Bad Request.*
	$.component[1].valueQuantity.value		${-1}		0		422		There was a problem saving the compositionWrong Status code. Expected: .200, 201, 204.. Got: 400. Error message.*value is not within interval, expected:0.0 <= -1.0 < 1000.0.*Bad Request.*
	


	$.component[1].valueQuantity.value		foobar		2		422		The value 'foobar' is not a valid decimal
	...																	Observation.component[1].value.ofType(Quantity).value
	
	$.component[1].valueQuantity.unit		missing		1		422		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.1..value.ofType.Quantity..unit': minimum required = 1, but only found 0
	...																	Observation.component[1].value.ofType(Quantity)
	
	$.component[1].valueQuantity.unit		${EMPTY}	1		422		@value cannot be empty
	...																	Observation.component[1].value.ofType(Quantity).unit
	
	$.component[1].valueQuantity.unit		${123}		0		422		Error parsing JSON: the primitive value must be a string
	...																	Observation.component[1].value[x].unit
	
	$.component[1].valueQuantity.system		missing		3		422		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.1..value.ofType.Quantity..system': minimum required = 1, but only found 0
	...																	Observation.component[1].value.ofType(Quantity)
	
	$.component[1].valueQuantity.system		${EMPTY}	2		422		@value cannot be empty
	...																	Observation.component[1].value.ofType(Quantity).system

	$.component[1].valueQuantity.system		foobar		2		422		Value is 'foobar' but must be 'http://unitsofmeasure.org'
	...																	Observation.component[1].value.ofType(Quantity).system

	$.component[1].valueQuantity.system		${123}		0		422		Error parsing JSON: the primitive value must be a string
	...																	Observation.component[1].value[x].system

	$.component[1].valueQuantity.code		missing		1		422		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.component.1..value.ofType.Quantity..code': minimum required = 1, but only found 0
	...																	Observation.component[1].value.ofType(Quantity)

	$.component[1].valueQuantity.code		${EMPTY}	1		422		@value cannot be empty
	...																	Observation.component[1].value.ofType(Quantity).code

	$.component[1].valueQuantity.code		${123}		0		422		Error parsing JSON: the primitive value must be a string
	...																	Observation.component[1].value[x].code

	$.component[1].valueQuantity.code		foobar		1		422		Value is 'foobar' but must be 'mm.Hg.'
	...																	Observation.component[1].value.ofType(Quantity).code






*** Keywords ***
create blood pressure with ehr reference
    # [Arguments]         ${json_path}  ${value}  ${issue_index}  ${http_status_code}  ${error_message}  ${location}=${None}

	# ehr.create new ehr    000_ehr_status.json
	# ${payload}=			generate payload from example json    ${json_path}    ${value}
	# # create blood pressure    ${payload}
	# observation.POST /Observation    Blood Pressure    ${payload}
	# observation.validate response - 422 (with error message)    ${issue_index}    ${http_status_code}    ${error_message}
	# ...															${location}
    
	[Arguments]         ${json_path}    ${value}    ${issue_index}    ${http_status_code}
	...					${error_message}    ${location}=${None}

						ehr.create new ehr    000_ehr_status.json
	${payload}=    		generate payload from example json    ${json_path}    ${value}
						observation.POST /Observation    Blood Pressure    ${payload}
						observation.validate response - 422 (with error message)    ${issue_index}
						...															${http_status_code}
						...															${error_message}
						...															${location}


create blood pressure w/o ehr reference    
	[Arguments]         ${json_path}    ${value}    ${issue_index}    ${http_status_code}
	...					${error_message}    ${location}=${None}

	${fake_ehr_ref}=	Evaluate    str(uuid.uuid4())    uuid
						Set Test Variable    ${subject_id}    ${fake_ehr_ref}
	${payload}=    		generate payload from example json    ${json_path}    ${value}
						observation.POST /Observation    Blood Pressure    ${payload}
						observation.validate response - 422 (with error message)    ${issue_index}
						...															${http_status_code}
						...															${error_message}
						...															${location}

generate payload from example json
	[Documentation]		Generates actual request payload using example json as a starting point.
	[Arguments]			${json_path}    ${value}

	${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/create-blood-pressure.json
                        Update Value To Json    ${payload}    $.subject.identifier.value    ${subject_id}
						Delete Object From Json    ${payload}    $.text

						# comment: delete field/object that has value 'missing' in test case table 
						Run Keyword And Return If   $value=="missing"
						...    Run Keyword    Delete Object From Json    ${payload}    ${json_path}
							Output Debug Info To Console    ${payload}

						# comment: set value from data table in test case
						Update Value To Json    ${payload}    ${json_path}    ${value}

	[Return]			${payload}
