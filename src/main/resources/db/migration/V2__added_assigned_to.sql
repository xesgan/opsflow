ALTER TABLE public.work_order
    ADD COLUMN assigned_to varchar(255);

ALTER TABLE public.work_order
    ADD CONSTRAINT work_order_assigned_to_check
        CHECK (
            status NOT IN ('ASSIGNED', 'IN_PROGRESS')
                OR assigned_to IS NOT NULL
            );
