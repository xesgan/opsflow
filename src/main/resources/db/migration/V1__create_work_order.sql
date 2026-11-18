CREATE TABLE public.work_order (
    id UUID primary key,
    created_at timestamp(6) without time zone,
    description varchar(255),
    status varchar(255),
    title varchar(255),
    CONSTRAINT work_order_status_check
                               CHECK ( status IN ('OPEN', 'ASSIGNED','IN_PROGRESS','RESOLVED','CLOSED','CANCELLED'))
);