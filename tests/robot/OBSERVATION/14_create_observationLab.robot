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
#${identifiervalue}              8302-2_BodyHeight
${vQSystem}						http://unitsofmeasure.org



*** Test Cases ***

