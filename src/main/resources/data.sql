INSERT INTO `WORK_TYPE` (
    `id`
    ,`type`
)
SELECT `id`, `type`
FROM (
            SELECT 1 AS `id`, 'テスト' AS `type` FROM dual
  UNION ALL SELECT 2 AS `id`, 'コーディング' AS `type` FROM dual
  UNION ALL SELECT 3 AS `id`, '要件定義' AS `type` FROM dual
  UNION ALL SELECT 4 AS `id`, 'ミーティング' AS `type` FROM dual
) WHERE NOT EXISTS (
    SELECT `id` FROM `WORK_TYPE`
    WHERE `type` = 'テスト' OR `type` = 'コーディング' OR `type` = '要件定義' OR `type` ='ミーティング'
);


INSERT INTO `DAILYREPORT_TYPE` (
    `id`
    ,`progress`
)
SELECT `id`, `progress`
FROM (
            SELECT 1 AS `id`, '0%' AS `progress` FROM dual
  UNION ALL SELECT 2 AS `id`, '10%' AS `progress` FROM dual
  UNION ALL SELECT 3 AS `id`, '20%' AS `progress` FROM dual
  UNION ALL SELECT 4 AS `id`, '30%' AS `progress` FROM dual
  UNION ALL SELECT 5 AS `id`, '40%' AS `progress` FROM dual
  UNION ALL SELECT 6 AS `id`, '50%' AS `progress` FROM dual
  UNION ALL SELECT 7 AS `id`, '60%' AS `progress` FROM dual
  UNION ALL SELECT 8 AS `id`, '70%' AS `progress` FROM dual
  UNION ALL SELECT 9 AS `id`, '80%' AS `progress` FROM dual
  UNION ALL SELECT 10 AS `id`, '90%' AS `progress` FROM dual
  UNION ALL SELECT 11 AS `id`, '100%' AS `progress` FROM dual
) WHERE NOT EXISTS (
    SELECT `id` FROM `DAILYREPORT_TYPE`
    WHERE `progress` = '0%' OR `progress` = '10%' OR `progress` = '20%' OR `progress` ='30%' OR `progress` ='40%' OR `progress` ='50%' OR `progress` ='60%' OR `progress` ='70%' OR `progress` ='80%' OR `progress` ='90%' OR `progress` ='100%'
);



INSERT INTO USERS(
	USERNAME,
	PASSWORD,
	ENABLED
	)SELECT USERNAME,PASSWORD,ENABLED
	FROM( SELECT 'admin' AS USERNAME, '{bcrypt}$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG' AS PASSWORD,true AS ENABLED FROM dual)
	WHERE NOT EXISTS(
		SELECT USERNAME FROM USERS
		WHERE PASSWORD = '{bcrypt}$2a$10$vC.r53zKYPwEXplBYH3mxuZP52r2u3udRcEg9yTUmwYE5yjmoUXyG' AND ENABLED = true);
    
     


INSERT INTO AUTHORITIES(
	USERNAME,
	AUTHORITY
	)SELECT USERNAME,AUTHORITY
	FROM( SELECT 'admin' AS USERNAME,  'ROLE_ADMIN' AS AUTHORITY FROM dual)
    WHERE NOT EXISTS(
    	SELECT USERNAME FROM AUTHORITIES
    	WHERE AUTHORITY ='ROLE_ADMIN');

