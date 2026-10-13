# Reserve surplus food

## User story

As a community pantry coordinator, I want to reserve available surplus food so that it can be
distributed to people in the community.

## Acceptance criteria

1. A reservation must request at least five portions.
2. If fewer than five portions are requested, the reservation is rejected.
3. If the donation has fewer portions than requested, the reservation is rejected.
4. If enough portions are available, the repository records the donation identifier, organisation
   identifier, and number of portions requested.
5. The service returns `true` for a successful reservation and `false` for a rejected reservation.
