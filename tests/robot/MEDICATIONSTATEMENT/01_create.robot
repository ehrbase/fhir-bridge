# Copyright (c) 2020 Peter Wohlfarth (Appsfactory GmbH), Wladislaw Wagner (Vitasystems GmbH),
# Dave Petzold (Appsfactory GmbH) & Pauline Schulz (Appsfactory GmbH)
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
...						Authorization=${AUTHORIZATION['Authorization']}

Force Tags              medicationstatement_create    create



*** Variables ***




*** Test Cases ***
001 Create Pharmacological Therapy (Active)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pharmacological-therapy.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to MedicationStatement endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	pharmacological-therapy    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    medicationstatement.create pharmacological therapy    Pharmacological Therapy (Active)    create-pharmacological-therapy.json
    medicationstatement.validate response - 201


002 Create Pharmacological Therapy (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pharmacological-therapy.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with unknown
    ...                 5. *POST* example JSON to MedicationStatement endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	pharmacological-therapy    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    medicationstatement.create pharmacological therapy with status change    Pharmacological Therapy (Unknown)    unknown    create-pharmacological-therapy.json
    medicationstatement.validate response - 201


003 Create Pharmacological Therapy (Stopped)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pharmacological-therapy.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with stopped
    ...                 5. *POST* example JSON to MedicationStatement endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	pharmacological-therapy    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    medicationstatement.create pharmacological therapy with status change    Pharmacological Therapy (Stopped)    stopped    create-pharmacological-therapy.json
    medicationstatement.validate response - 201


004 Create Pharmacological Therapy (Not-Taken)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pharmacological-therapy.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with not-taken
    ...                 5. *POST* example JSON to MedicationStatement endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	pharmacological-therapy    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    medicationstatement.create pharmacological therapy with status change    Pharmacological Therapy (Not-Taken)    not-taken    create-pharmacological-therapy.json
    medicationstatement.validate response - 201


005 Create Pharmacological Therapy ACE Inhibitors (Active)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pharmacological-therapy-ace-inhibitors-active.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to MedicationStatement endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	pharmacological-therapy-ace-inhibitors    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    medicationstatement.create pharmacological therapy    Pharmacological Therapy ACE Inhibitors (Active)    create-pharmacological-therapy-ace-inhibitors-active.json
    medicationstatement.validate response - 201


006 Create Pharmacological Therapy ACE Inhibitors (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pharmacological-therapy-ace-inhibitors-active.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with unknown
    ...                 5. *POST* example JSON to MedicationStatement endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	pharmacological-therapy-ace-inhibitors    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    medicationstatement.create pharmacological therapy with status change    Pharmacological Therapy ACE Inhibitors (Unknown)    unknown    create-pharmacological-therapy-ace-inhibitors-active.json
    medicationstatement.validate response - 201


007 Create Pharmacological Therapy ACE Inhibitors (Stopped)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pharmacological-therapy-ace-inhibitors-active.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with stopped
    ...                 5. *POST* example JSON to MedicationStatement endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	pharmacological-therapy-ace-inhibitors    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    medicationstatement.create pharmacological therapy with status change    Pharmacological Therapy ACE Inhibitors (Stopped)    stopped    create-pharmacological-therapy-ace-inhibitors-active.json
    medicationstatement.validate response - 201


008 Create Pharmacological Therapy ACE Inhibitors (Not-Taken)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pharmacological-therapy-ace-inhibitors-active.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with not-taken
    ...                 5. *POST* example JSON to MedicationStatement endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	pharmacological-therapy-ace-inhibitors    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    medicationstatement.create pharmacological therapy with status change    Pharmacological Therapy ACE Inhibitors (Not-Taken)    not-taken    create-pharmacological-therapy-ace-inhibitors-active.json
    medicationstatement.validate response - 201


009 Create Pharmacological Therapy Anticoagulants (Active)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pharmacological-therapy-anticoagulants.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to MedicationStatement endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	pharmacological-therapy-anticoagulants    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    medicationstatement.create pharmacological therapy    Pharmacological Therapy Anticoagulants (Active)    create-pharmacological-therapy-anticoagulants.json
    medicationstatement.validate response - 201


010 Create Pharmacological Therapy Anticoagulants (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pharmacological-therapy-anticoagulants.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with unknown
    ...                 5. *POST* example JSON to MedicationStatement endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	pharmacological-therapy-anticoagulants    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    medicationstatement.create pharmacological therapy with status change    Pharmacological Therapy Anticoagulants (Unknown)    unknown    create-pharmacological-therapy-anticoagulants.json
    medicationstatement.validate response - 201


011 Create Pharmacological Therapy Anticoagulants (Stopped)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pharmacological-therapy-anticoagulants.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with stopped
    ...                 5. *POST* example JSON to MedicationStatement endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	pharmacological-therapy-anticoagulants    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    medicationstatement.create pharmacological therapy with status change    Pharmacological Therapy Anticoagulants (Stopped)    stopped    create-pharmacological-therapy-anticoagulants.json
    medicationstatement.validate response - 201


012 Create Pharmacological Therapy Anticoagulants (Not-Taken)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pharmacological-therapy-anticoagulants.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with not-taken
    ...                 5. *POST* example JSON to MedicationStatement endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	pharmacological-therapy-anticoagulants    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    medicationstatement.create pharmacological therapy with status change    Pharmacological Therapy Anticoagulants (Not-Taken)    not-taken    create-pharmacological-therapy-anticoagulants.json
    medicationstatement.validate response - 201


013 Create Pharmacological Therapy Immunoglobulins (Active)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pharmacological-therapy-immunoglobulins.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to MedicationStatement endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	pharmacological-therapy-anticoagulants    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    medicationstatement.create pharmacological therapy    Pharmacological Therapy Immunoglobulins    create-pharmacological-therapy-immunoglobulins.json
    medicationstatement.validate response - 201


014 Create Pharmacological Therapy Immunoglobulins (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pharmacological-therapy-immunoglobulins.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with unknown
    ...                 5. *POST* example JSON to MedicationStatement endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	pharmacological-therapy-ace-inhibitors    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    medicationstatement.create pharmacological therapy with status change    Pharmacological Therapy Immunoglobulins (Unknown)    unknown    create-pharmacological-therapy-immunoglobulins.json
    medicationstatement.validate response - 201



015 Create Pharmacological Therapy Immunoglobulins (Stopped)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pharmacological-therapy-immunoglobulins.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with stopped
    ...                 5. *POST* example JSON to MedicationStatement endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	pharmacological-therapy-ace-inhibitors    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    medicationstatement.create pharmacological therapy with status change    Pharmacological Therapy Immunoglobulins (Stopped)    stopped    create-pharmacological-therapy-immunoglobulins.json
    medicationstatement.validate response - 201



016 Create Pharmacological Therapy Immunoglobulins (Not-Taken)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-pharmacological-therapy-immunoglobulins.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with not-taken
    ...                 5. *POST* example JSON to MedicationStatement endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	pharmacological-therapy-ace-inhibitors    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    medicationstatement.create pharmacological therapy with status change    Pharmacological Therapy Immunoglobulins (Not-Taken)    not-taken    create-pharmacological-therapy-immunoglobulins.json
    medicationstatement.validate response - 201
