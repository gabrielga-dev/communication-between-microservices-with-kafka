CREATE INDEX idx_tbl_log_topic ON log (topic);

CREATE INDEX idx_tbl_log_type ON log (type);

CREATE INDEX idx_tbl_log_topic_type ON log (topic, type);