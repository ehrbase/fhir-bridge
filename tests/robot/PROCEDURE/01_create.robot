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

Force Tags              procedure_create    create



*** Variables ***




*** Test Cases ***
001 Create Procedure
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-procedure.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             valid

	ehr.create new ehr    000_ehr_status.json
	procedure.create procedure    create-procedure.json
    procedure.validate response - 201


002 Create Radiology Procedures - Chest X-Ray
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-radiology-procedures-chest-x-ray.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	radiology-procedures    chest-x-ray    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create radiology procedures    Radiology Procedures - Chest X-Ray    create-radiology-procedures-chest-x-ray.json
    procedure.validate response - 201


003 Create Radiology Procedures - Computed Tomography
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-radiology-procedures-computed-tomography.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	radiology-procedures    computed-tomography    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create radiology procedures    Radiology Procedures - Computed Tomography    create-radiology-procedures-computed-tomography.json
    procedure.validate response - 201


004 Create Radiology Procedures - Ultrasound
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-radiology-procedures-ultrasound.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	radiology-procedures    ultrasound    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create radiology procedures    Radiology Procedures - Ultrasound    create-radiology-procedures-ultrasound.json
    procedure.validate response - 201


005 Create Generic Therapy - Dialysis with not performed "performedPeriod"
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-dialysis-with-performedPeriod-not-performed.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    dialysis    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create dialysis    Generic Therapy - Dialysis with not performed "performedPeriod"    create-dialysis-with-performedPeriod-not-performed.json
    procedure.validate response - 201


006 Create Generic Therapy - Dialysis with not performed "performedDateTime"
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-dialysis-with-performedDateTime-not-performed.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    dialysis    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create dialysis    Generic Therapy - Dialysis with not performed "performedDateTime"    create-dialysis-with-performedDateTime-not-performed.json
    procedure.validate response - 201


007 Create Generic Therapy - Dialysis with unknown "performedDateTime" (Status: In-Progress)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-dialysis-with-performedDateTime-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    dialysis    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create dialysis    Generic Therapy - Dialysis with unknown "performedDateTime" (Status: In-Progress)    create-dialysis-with-performedDateTime-unknown.json
    procedure.validate response - 201


008 Create Generic Therapy - Dialysis with unknown "performedDateTime" (Status: Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-dialysis-with-performedDateTime-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with unknown
    ...                 5. *POST* example JSON to Procedure endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	generic-therapy    dialysis    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create dialysis with status change    Generic Therapy - Dialysis with unknown "performedDateTime" (Status: Unknown)    unknown    create-dialysis-with-performedDateTime-unknown.json
    procedure.validate response - 201


009 Create Generic Therapy - Dialysis with unknown "performedDateTime" (Status: Completed)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-dialysis-with-performedDateTime-unknown.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with Completed
    ...                 5. *POST* example JSON to Procedure endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	generic-therapy    dialysis    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create dialysis with status change    Generic Therapy - Dialysis with unknown "performedDateTime" (Status: Completed)    completed    create-dialysis-with-performedDateTime-unknown.json
    procedure.validate response - 201


010 Create Generic Therapy - Prone Position (Not-Done)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-prone-position-not-done.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    prone-position    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create prone position    Generic Therapy - Prone Position (Not-Done)    create-prone-position-not-done.json
    procedure.validate response - 201


011 Create Generic Therapy - Prone Position (In-Progress)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-prone-position-in-progress.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    prone-position    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create prone position    Generic Therapy - Prone Position (In-Progress)    create-prone-position-in-progress.json
    procedure.validate response - 201


012 Create Generic Therapy - Prone Position (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-prone-position-in-progress.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with Unknown
    ...                 5. *POST* example JSON to Procedure endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	generic-therapy    prone-position    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create prone position with status change    Generic Therapy - Prone Position (Unknown)    unknown    create-prone-position-in-progress.json
    procedure.validate response - 201


013 Create Generic Therapy - Prone Position (Completed)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-prone-position-in-progress.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with Completed
    ...                 5. *POST* example JSON to Procedure endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	generic-therapy    prone-position    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create prone position with status change    Generic Therapy - Prone Position (Completed)    completed    create-prone-position-in-progress.json
    procedure.validate response - 201


014 Create Generic Therapy - Extracorporeal Membrane Oxygenation (Not-Done)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-extracorporeal-membrane-oxygenation-no-done.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    extracorporeal-membrane-oxygenation    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create prone position    Generic Therapy - Extracorporeal Membrane Oxygenation (Not-Done)    create-extracorporeal-membrane-oxygenation-no-done.json
    procedure.validate response - 201


015 Create Generic Therapy - Extracorporeal Membrane Oxygenation (In-Progress)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-extracorporeal-membrane-oxygenation-in-progress.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    extracorporeal-membrane-oxygenation    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create prone position    Generic Therapy - Extracorporeal Membrane Oxygenation (In-Progress)    create-extracorporeal-membrane-oxygenation-in-progress.json
    procedure.validate response - 201


016 Create Generic Therapy - Extracorporeal Membrane Oxygenation (Completed)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-extracorporeal-membrane-oxygenation-in-progress.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with Completed
    ...                 5. *POST* example JSON to Procedure endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	generic-therapy    extracorporeal-membrane-oxygenation    valid    not-ready    not-implemented 

    ehr.create new ehr    000_ehr_status.json
    procedure.create extracorporeal membrane oxygenation with status change    Generic Therapy - Extracorporeal Membrane Oxygenation (Completed)    completed    create-extracorporeal-membrane-oxygenation-in-progress.json
    procedure.validate response - 201


017 Create Generic Therapy - Extracorporeal Membrane Oxygenation (Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-extracorporeal-membrane-oxygenation-in-progress.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with Unknown
    ...                 5. *POST* example JSON to Procedure endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	generic-therapy    extracorporeal-membrane-oxygenation    valid    not-ready    not-implemented 

    ehr.create new ehr    000_ehr_status.json
    procedure.create extracorporeal membrane oxygenation with status change    Generic Therapy - Extracorporeal Membrane Oxygenation (Unknown)    unknown    create-extracorporeal-membrane-oxygenation-in-progress.json
    procedure.validate response - 201


018 Create Generic Therapy - Respiratory Therapies - Invasive Ventilation (Tracheotomy)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-respiratory-therapies-invasive-ventilation-tracheotomy.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    respiratory-therapies    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create prone position    Generic Therapy - Respiratory Therapies - Invasive Ventilation (Tracheotomy)    create-respiratory-therapies-invasive-ventilation-tracheotomy.json
    procedure.validate response - 201


019 Create Generic Therapy - Respiratory Therapies - Invasive Ventilation (Orotracheal)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-respiratory-therapies-invasive-ventilation-orotracheal.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    respiratory-therapies    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create prone position    Generic Therapy - Respiratory Therapies - Invasive Ventilation (Orotracheal)    create-respiratory-therapies-invasive-ventilation-orotracheal.json
    procedure.validate response - 201


020 Create Generic Therapy - Respiratory Therapies - Artificial Respiration (Status: Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-respiratory-therapies-artificial-respiration.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    respiratory-therapies    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create respiratory therapies    Generic Therapy - Respiratory Therapies - Artificial Respiration (Status: Unknown)    create-respiratory-therapies-artificial-respiration.json
    procedure.validate response - 201


021 Create Generic Therapy - Extracorporeal Membrane Oxygenation (In-Progress)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-respiratory-therapies-artificial-respiration.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with in-progress
    ...                 5. *POST* example JSON to Procedure endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	generic-therapy    respiratory-therapies    valid    not-ready     not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create respiratory therapies with status change    Generic Therapy - Extracorporeal Membrane Oxygenation (In-Progress)    in-progress    create-respiratory-therapies-artificial-respiration.json
    procedure.validate response - 201


022 Create Generic Therapy - Extracorporeal Membrane Oxygenation (Not-Done)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-respiratory-therapies-artificial-respiration.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with not-done
    ...                 5. *POST* example JSON to Procedure endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	generic-therapy    respiratory-therapies    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create respiratory therapies with status change    Generic Therapy - Extracorporeal Membrane Oxygenation (Not-Done)    not-done    create-respiratory-therapies-artificial-respiration.json
    procedure.validate response - 201


023 Create Generic Therapy - Respiratory Therapies - Nasal High-Flow-Oxygen-Therapy
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-respiratory-therapies-nasal-high-flow-oxygen-therapy.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    respiratory-therapies    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create respiratory therapies    Generic Therapy - Respiratory Therapies - Nasal High-Flow-Oxygen-Therapy    create-respiratory-therapies-nasal-high-flow-oxygen-therapy.json
    procedure.validate response - 201


024 Create Generic Therapy - Respiratory Therapies - Non-Invasive Ventilation
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-respiratory-therapies-non-invasive-ventilation.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    respiratory-therapies    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create respiratory therapies    Generic Therapy - Respiratory Therapies - Non-Invasive Ventilation    create-respiratory-therapies-non-invasive-ventilation.json
    procedure.validate response - 201


025 Create Generic Therapy - Respiratory Therapies with valid performedDateTime (Status: In-Progress)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-respiratory-therapies-with-valid-performeddatetime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    respiratory-therapies    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create respiratory therapies    Generic Therapy - Respiratory Therapies with valid performedDateTime (Status: In-Progress)    create-respiratory-therapies-with-valid-performeddatetime.json
    procedure.validate response - 201


026 Create Generic Therapy - Respiratory Therapies with valid performedPeriod (Status: In-Progress)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-respiratory-therapies-with-valid-performedperiod.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    respiratory-therapies    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create respiratory therapies    Generic Therapy - Respiratory Therapies with valid performedPeriod (Status: In-Progress)    create-respiratory-therapies-with-valid-performedperiod.json
    procedure.validate response - 201


027 Create Generic Therapy - Respiratory Therapies with unknown performedDateTime (Status: Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-respiratory-therapies-with-unknown-performedDateTime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    respiratory-therapies    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create respiratory therapies    Generic Therapy - Respiratory Therapies with unknown performedDateTime (Status: Unknown)    create-respiratory-therapies-with-unknown-performedDateTime.json
    procedure.validate response - 201


028 Create Generic Therapy - Respiratory Therapies with not-performed performedDateTime (Status: Not-Done)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-respiratory-therapies-with-not-performed-performedDateTime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    respiratory-therapies    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create respiratory therapies    Generic Therapy - Respiratory Therapies with not-performed performedDateTime (Status: Not-Done)    create-respiratory-therapies-with-not-performed-performedDateTime.json
    procedure.validate response - 201

029 Create Generic Therapy - Apheresis with not-performed performedDateTime (Status: Not-Done)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-apheresis-with-not-performed-performedDateTime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
    ...                 4. *POST* example JSON to procedure endpoint\n\n
	...                 5. *VALIDATE* the response status
	[Tags]             	generic-therapy    apheresis    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create apheresis    Generic Therapy - Apheresis with not-performed performedDateTime (Status: Not-Done)    create-apheresis-with-not-performed-performedDateTime.json
    procedure.validate response - 201


030 Create Generic Therapy - Apheresis with unknown performedDateTime (Status: Completed)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-apheresis-with-not-unknown-performedDateTime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with completed
    ...                 5. *POST* example JSON to Procedure endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	generic-therapy    apheresis    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create apheresis with status change    Apheresis with unknown performedDateTime (Status: Completed)    completed    create-apheresis-with-not-unknown-performedDateTime.json
    procedure.validate response - 201


031 Create Generic Therapy - Apheresis with unknown performedDateTime (Status: Unknown)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-apheresis-with-not-unknown-performedDateTime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with unknown
    ...                 5. *POST* example JSON to Procedure endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	generic-therapy    apheresis    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create apheresis with status change    Apheresis with unknown performedDateTime (Status: Unknown)    unknown    create-apheresis-with-not-unknown-performedDateTime.json
    procedure.validate response - 201


032 Create Generic Therapy - Apheresis with unknown performedDateTime (Status: In-Progress)
	[Documentation]     1. *CREATE* new EHR record\n\n 
	...                 2. *LOAD* _create-apheresis-with-not-unknown-performedDateTime.json_\n\n
	...                 3. *UPDATE* ``Subject - Identifier - value`` with the _UUID:_ ${subject_id} which was created in EHR record\n\n
	...					4. *UPDATE* ``Status`` with in-progress
    ...                 5. *POST* example JSON to Procedure endpoint\n\n
	...                 6. *VALIDATE* the response status
	[Tags]             	generic-therapy    apheresis    valid    not-ready    not-implemented

    ehr.create new ehr    000_ehr_status.json
    procedure.create apheresis with status change    Apheresis with unknown performedDateTime (Status: In-Progress)    in-progress    create-apheresis-with-not-unknown-performedDateTime.json
    procedure.validate response - 201
