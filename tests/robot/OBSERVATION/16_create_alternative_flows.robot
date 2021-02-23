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
...															   Authorization=${AUTHORIZATION['Authorization']}

Force Tags              observation_create    create



*** Variables ***




*** Test Cases ***


001 Create Observation Blood Pressure loinc datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-blood-pressure_loinc-datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	blood-pressure    valid    alternative    loinc    datetime

    ehr.create new ehr    000_ehr_status.json
    observation.create blood pressure  Blood Pressure  create-blood-pressure_loinc-datetime.json
    observation.validate response - 201


002 Create Observation Blood Pressure loinc instant
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-blood-pressure_loinc-instant.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	blood-pressure    valid    alternative   loinc    instant    not-ready_bug    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create blood pressure  Blood Pressure  create-blood-pressure_loinc-instant.json
    observation.validate response - 201



003 Create Observation Blood Pressure loinc period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-blood-pressure_loinc-period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	blood-pressure    valid    alternative    loinc    period    not-ready_bug    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create blood pressure  Blood Pressure  create-blood-pressure_loinc-period.json
    observation.validate response - 201



004 Create Observation Blood Pressure loinc timing
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-blood-pressure_loinc-timing.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	blood-pressure    valid    alternative    loinc    timing    not-ready_bug    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create blood pressure  Blood Pressure  create-blood-pressure_loinc-timing.json
    observation.validate response - 201



005 Create Observation Blood Pressure snomed datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-blood-pressure_snomed-datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	blood-pressure    valid    alternative    snomed    datetime    not-ready_bug    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create blood pressure  Blood Pressure  create-blood-pressure_snomed-datetime.json
    observation.validate response - 201



006 Create Observation Blood Pressure snomed instant
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-blood-pressure_snomed-instant.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	blood-pressure    valid    alternative    snomed    instant    not-ready_bug    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create blood pressure  Blood Pressure  create-blood-pressure_snomed-instant.json
    observation.validate response - 201



007 Create Observation Blood Pressure snomed period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-blood-pressure_snomed-period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	blood-pressure    valid    alternative    snomed    period    not-ready_bug    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create blood pressure  Blood Pressure  create-blood-pressure_snomed-period.json
    observation.validate response - 201



008 Create Observation Blood Pressure snomed timing
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-blood-pressure_snomed-timing.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	blood-pressure    valid    alternative    snomed    timing    not-ready_bug    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create blood pressure  Blood Pressure  create-blood-pressure_snomed-timing.json
    observation.validate response - 201




009 Create Observation Body Height loinc datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-height_loinc-datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-height    valid    alternative    loinc    datetime

    ehr.create new ehr    000_ehr_status.json
    observation.create body height  Body Height  create-body-height_loinc-datetime.json
    observation.validate response - 201


010 Create Observation Body Height loinc period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-height_loinc-period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-height    valid    alternative    loinc    period    not-ready_bug    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create body height  Body Height  create-body-height_loinc-period.json
    observation.validate response - 201



011 Create Observation Body Height loinc period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-height_loinc-period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-height    valid    alternative    loinc    period_2    not-ready_bug    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create body height  Body Height  create-body-height_loinc-period_2.json
    observation.validate response - 201



012 Create Observation Body Height snomed datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-height_snomed-datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-height    valid    alternative    snomed    datetime    not-ready_bug    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create body height  Body Height  create-body-height_snomed-datetime.json
    observation.validate response - 201



013 Create Observation Body Height snomed period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-height_snomed-period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-height    valid    alternative    snomed    period    not-ready_bug    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create body height  Body Height  create-body-height_snomed-period.json
    observation.validate response - 201



014 Create Observation Body Height snomed period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-height_snomed-period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-height    valid    alternative    snomed    period_2    not-ready_bug    not-ready

    ehr.create new ehr    000_ehr_status.json
    observation.create body height  Body Height  create-body-height_snomed-period_2.json
    observation.validate response - 201



015 Create Heart Rate loinc datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-heart-rate-loinc-datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	heart-rate    valid    alternative    datetime    loinc

    ehr.create new ehr    000_ehr_status.json
    observation.create heart rate  Heart Rate  create-heart-rate-loinc-datetime.json
    observation.validate response - 201


016 Create Heart Rate loinc period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-heart-rate-loinc-period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	heart-rate    valid    alternative    period    loinc    not-ready    not-ready_bug

    ehr.create new ehr    000_ehr_status.json
    observation.create heart rate  Heart Rate  create-heart-rate-loinc-period.json
    observation.validate response - 201



017 Create Heart Rate loinc period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-heart-rate-loinc-period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	heart-rate    valid    alternative    period_2    loinc    not-ready    not-ready_bug

    ehr.create new ehr    000_ehr_status.json
    observation.create heart rate  Heart Rate  create-heart-rate-loinc-period_2.json
    observation.validate response - 201



018 Create Heart Rate snomed datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-heart-rate-snomed-datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	heart-rate    valid    alternative    datetime    snomed

    ehr.create new ehr    000_ehr_status.json
    observation.create heart rate  Heart Rate  create-heart-rate-snomed-datetime.json
    observation.validate response - 201



019 Create Heart Rate snomed period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-heart-rate-loinc-period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	heart-rate    valid    alternative    period    snomed    not-ready    not-ready_bug

    ehr.create new ehr    000_ehr_status.json
    observation.create heart rate  Heart Rate  create-heart-rate-loinc-period.json
    observation.validate response - 201



020 Create Heart Rate snomed period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-heart-rate-loinc-period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	heart-rate    valid    alternative    period_2    snomed    not-ready    not-ready_bug

    ehr.create new ehr    000_ehr_status.json
    observation.create heart rate  Heart Rate  create-heart-rate-loinc-period_2.json
    observation.validate response - 201



021 Create Body Weight loinc datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-weight-loinc-datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-weight    valid    alternative    loinc    datetime

    ehr.create new ehr    000_ehr_status.json
    observation.create body weight  Body Weight  create-body-weight-loinc-datetime.json
    observation.validate response - 201



022 Create Body Weight loinc period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-weight-loinc-period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-weight    valid    alternative    loinc    period    not-ready    not-ready_bug

    ehr.create new ehr    000_ehr_status.json
    observation.create body weight  Body Weight  create-body-weight-loinc-period.json
    observation.validate response - 201



023 Create Body Weight loinc period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-weight-loinc-period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-weight    valid    alternative   loinc    period_2     not-ready    not-ready_bug

    ehr.create new ehr    000_ehr_status.json
    observation.create body weight  Body Weight  create-body-weight-loinc-period_2.json
    observation.validate response - 201



024 Create Body Weight snomed datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-weight    valid    alternative    snomed    datetime

    ehr.create new ehr    000_ehr_status.json
    observation.create body weight  Body Weight  create-body-weight-snomed-datetime.json
    observation.validate response - 201



025 Create Body Weight snomed period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-weight-snomed-period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-weight    valid    alternative    snomed    period    not-ready    not-ready_bug

    ehr.create new ehr    000_ehr_status.json
    observation.create body weight  Body Weight  create-body-weight-snomed-period.json
    observation.validate response - 201



026 Create Body Weight snomed period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-weight-snomed-period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-weight    valid    alternative    snomed    period_2    not-ready    not-ready_bug

    ehr.create new ehr    000_ehr_status.json
    observation.create body weight  Body Weight  create-body-weight-snomed-period_2.json
    observation.validate response - 201



027 Create Body Temperature loinc datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-temp-loinc-datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-temperature    valid    alternative    loinc    datetime

    ehr.create new ehr    000_ehr_status.json
    observation.create body temperature  Body Temperature  create-body-temp-loinc-datetime.json
    observation.validate response - 201



028 Create Body Temperature loinc period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-temp-loinc-period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-temperature    valid    alternative    loinc    period    not-ready    not-ready_bug

    ehr.create new ehr    000_ehr_status.json
    observation.create body temperature  Body Temperature  create-body-temp-loinc-period.json
    observation.validate response - 201



029 Create Body Temperature loinc period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-temp-loinc-period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-temperature    valid    alternative    loinc    period_2    not-ready    not-ready_bug

    ehr.create new ehr    000_ehr_status.json
    observation.create body temperature  Body Temperature  create-body-temp-loinc-period_2.json
    observation.validate response - 201



030 Create Body Temperature snomed datetime
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-temp-snomed-datetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-temperature    valid    alternative    snomed    datetime

    ehr.create new ehr    000_ehr_status.json
    observation.create body temperature  Body Temperature  create-body-temp-snomed-datetime.json
    observation.validate response - 201



031 Create Body Temperature snomed period
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-temp-snomed-period.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-temperature    valid    alternative    snomed    period    not-ready    not-ready_bug

    ehr.create new ehr    000_ehr_status.json
    observation.create body temperature  Body Temperature  create-body-temp-snomed-period.json
    observation.validate response - 201



032 Create Body Temperature snomed period_2
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-body-temp-snomed-period_2.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to observation endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	body-temperature    valid    alternative    snomed    period_2    not-ready    not-ready_bug

    ehr.create new ehr    000_ehr_status.json
    observation.create body temperature  Body Temperature  create-body-temp-snomed-period_2.json
    observation.validate response - 201



