USE [BuildingSiteManagement]
GO
/****** Object:  Table [dbo].[Apartment]    Script Date: 24.01.2023 14:13:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Apartment](
	[apartmentID] [int] IDENTITY(1,1) NOT NULL,
	[apartmentName] [varchar](55) NOT NULL,
	[street] [varchar](25) NULL,
	[city] [varchar](25) NULL,
	[state] [varchar](10) NULL,
	[zip_code] [char](5) NULL,
	[residentCounter] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[apartmentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AssistantManager]    Script Date: 24.01.2023 14:13:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AssistantManager](
	[assistantManagerID] [int] IDENTITY(1,1) NOT NULL,
	[personID] [int] NULL,
	[managerID] [int] NULL,
	[residentID] [int] NULL,
	[assistantManagerName] [varchar](55) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[assistantManagerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Bill]    Script Date: 24.01.2023 14:13:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bill](
	[billID] [int] IDENTITY(1,1) NOT NULL,
	[billDesc] [varchar](50) NULL,
	[subscriptionID] [int] NULL,
	[billAmount] [float] NULL,
	[billImage] [image] NULL,
 CONSTRAINT [PK_Bill] PRIMARY KEY CLUSTERED 
(
	[billID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Controller]    Script Date: 24.01.2023 14:13:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Controller](
	[controllerID] [int] IDENTITY(1,1) NOT NULL,
	[personID] [int] NULL,
	[residentID] [int] NULL,
	[controllerName] [varchar](55) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[controllerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Decisions]    Script Date: 24.01.2023 14:13:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Decisions](
	[DecisionID] [int] IDENTITY(1,1) NOT NULL,
	[ApartmentID] [int] NULL,
	[DecisionDescription] [varchar](50) NULL,
	[VoteYes] [int] NULL,
	[VoteNo] [int] NULL,
	[DecisionDate] [date] NULL,
	[isAccepted] [varchar](50) NULL,
	[isFullOrMajority] [varchar](50) NULL,
 CONSTRAINT [PK_Decisions] PRIMARY KEY CLUSTERED 
(
	[DecisionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Expense]    Script Date: 24.01.2023 14:13:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Expense](
	[ExpenseId] [int] IDENTITY(1,1) NOT NULL,
	[ReceiptId] [int] NULL,
	[ControllerId] [int] NULL,
 CONSTRAINT [PK_Expense] PRIMARY KEY CLUSTERED 
(
	[ExpenseId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Manager]    Script Date: 24.01.2023 14:13:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Manager](
	[managerID] [int] IDENTITY(1,1) NOT NULL,
	[personID] [int] NULL,
	[apartmentID] [int] NULL,
	[residentID] [int] NULL,
	[managerName] [varchar](55) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[managerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Message]    Script Date: 24.01.2023 14:13:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Message](
	[MessageID] [int] IDENTITY(1,1) NOT NULL,
	[ManagerID] [int] NULL,
	[ResidentID] [int] NULL,
	[Message] [varchar](100) NULL,
	[SendDate] [date] NULL,
 CONSTRAINT [PK_Message] PRIMARY KEY CLUSTERED 
(
	[MessageID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[movedFlat]    Script Date: 24.01.2023 14:13:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[movedFlat](
	[flatHistory] [int] IDENTITY(1,1) NOT NULL,
	[oldFlatID] [int] NULL,
	[newFlatID] [int] NULL,
	[apartmentID] [int] NULL,
	[enteranceDate] [date] NULL,
	[leavingDate] [date] NULL,
 CONSTRAINT [PK_movedFlat] PRIMARY KEY CLUSTERED 
(
	[flatHistory] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Payment]    Script Date: 24.01.2023 14:13:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Payment](
	[paymentID] [int] IDENTITY(1,1) NOT NULL,
	[receiptID] [int] NULL,
	[residentID] [int] NULL,
 CONSTRAINT [PK_Payment] PRIMARY KEY CLUSTERED 
(
	[paymentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Person]    Script Date: 24.01.2023 14:13:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Person](
	[personID] [int] IDENTITY(1,1) NOT NULL,
	[apartmentID] [int] NULL,
	[personName] [varchar](55) NULL,
	[personStatus] [tinyint] NULL,
PRIMARY KEY CLUSTERED 
(
	[personID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PersonStatus]    Script Date: 24.01.2023 14:13:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PersonStatus](
	[statusID] [tinyint] NOT NULL,
	[statusName] [varchar](50) NULL,
 CONSTRAINT [PK_PersonStatus] PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Receipt]    Script Date: 24.01.2023 14:13:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Receipt](
	[ReceiptID] [int] IDENTITY(1,1) NOT NULL,
	[ReceiptDescription] [varchar](50) NULL,
	[ResidentID] [int] NULL,
	[ReceiptAmount] [float] NULL,
	[ReceiptTime] [date] NULL,
 CONSTRAINT [PK_Reciept] PRIMARY KEY CLUSTERED 
(
	[ReceiptID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Resident]    Script Date: 24.01.2023 14:13:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Resident](
	[residentID] [int] IDENTITY(1,1) NOT NULL,
	[personID] [int] NULL,
	[apartmentID] [int] NULL,
	[residentName] [varchar](55) NOT NULL,
	[residentPhoneNum] [varchar](55) NOT NULL,
	[paidFlag] [int] NOT NULL,
	[flatId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[residentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subscription]    Script Date: 24.01.2023 14:13:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subscription](
	[SubscriptionID] [int] IDENTITY(1,1) NOT NULL,
	[SubscriptionType] [varchar](50) NULL,
	[managerID] [int] NULL,
 CONSTRAINT [PK_Subscriptions] PRIMARY KEY CLUSTERED 
(
	[SubscriptionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Apartment] ON 

INSERT [dbo].[Apartment] ([apartmentID], [apartmentName], [street], [city], [state], [zip_code], [residentCounter]) VALUES (3, N'Deniz', N'Bestekar', N'Ankara', N'Cankaya', N'06510', 11)
INSERT [dbo].[Apartment] ([apartmentID], [apartmentName], [street], [city], [state], [zip_code], [residentCounter]) VALUES (4, N'Beyazsaray', N'Ayvali', N'Ankara', N'Keçiören', N'06780', 11)
INSERT [dbo].[Apartment] ([apartmentID], [apartmentName], [street], [city], [state], [zip_code], [residentCounter]) VALUES (5, N'Yaprak', N'Esat', N'Ankara', N'Cankaya', N'6530 ', 11)
INSERT [dbo].[Apartment] ([apartmentID], [apartmentName], [street], [city], [state], [zip_code], [residentCounter]) VALUES (6, N'Cicek', N'Baglar', N'Ankara', N'Cankaya', N'06540', 12)
SET IDENTITY_INSERT [dbo].[Apartment] OFF
GO
SET IDENTITY_INSERT [dbo].[AssistantManager] ON 

INSERT [dbo].[AssistantManager] ([assistantManagerID], [personID], [managerID], [residentID], [assistantManagerName]) VALUES (276, 79, 115, 80, N'Cicek Demirci')
INSERT [dbo].[AssistantManager] ([assistantManagerID], [personID], [managerID], [residentID], [assistantManagerName]) VALUES (278, 89, 116, 90, N'İlkkan Yol')
INSERT [dbo].[AssistantManager] ([assistantManagerID], [personID], [managerID], [residentID], [assistantManagerName]) VALUES (280, 99, 117, 100, N'Mehmet Uzman')
INSERT [dbo].[AssistantManager] ([assistantManagerID], [personID], [managerID], [residentID], [assistantManagerName]) VALUES (281, 109, 118, 110, N'Ceyhun Gulselam')
SET IDENTITY_INSERT [dbo].[AssistantManager] OFF
GO
SET IDENTITY_INSERT [dbo].[Bill] ON 

INSERT [dbo].[Bill] ([billID], [billDesc], [subscriptionID], [billAmount], [billImage]) VALUES (1, N'Electricity(Elevator)', 1, 3200, 0x7777772E696D61676562696C6C2E636F6D2F696D616765456C6563744F74686572)
INSERT [dbo].[Bill] ([billID], [billDesc], [subscriptionID], [billAmount], [billImage]) VALUES (2, N'Electricity(Other)', 2, 2500, 0x7777772E696D61676562696C6C2E636F6D2F696D616765456C6563744F74686572)
INSERT [dbo].[Bill] ([billID], [billDesc], [subscriptionID], [billAmount], [billImage]) VALUES (3, N'Water Subscription', 3, 1100, 0x7777772E696D61676562696C6C2E636F6D2F696D616765456C6563744F74686572)
INSERT [dbo].[Bill] ([billID], [billDesc], [subscriptionID], [billAmount], [billImage]) VALUES (4, N'Water Subscription', 4, 850, 0x7777772E696D61676562696C6C2E636F6D2F696D616765456C6563744F74686572)
INSERT [dbo].[Bill] ([billID], [billDesc], [subscriptionID], [billAmount], [billImage]) VALUES (5, N'Electricity(Elevator)', 5, 3200, 0x7777772E696D61676562696C6C2E636F6D2F696D616765456C6563744F74686572)
INSERT [dbo].[Bill] ([billID], [billDesc], [subscriptionID], [billAmount], [billImage]) VALUES (6, N'Electricity(Other)', 6, 3500, 0x7777772E696D61676562696C6C2E636F6D2F696D616765456C6563744F74686572)
SET IDENTITY_INSERT [dbo].[Bill] OFF
GO
SET IDENTITY_INSERT [dbo].[Controller] ON 

INSERT [dbo].[Controller] ([controllerID], [personID], [residentID], [controllerName]) VALUES (819, 80, 81, N'Sadi Evren')
INSERT [dbo].[Controller] ([controllerID], [personID], [residentID], [controllerName]) VALUES (820, 90, 91, N'Hakkı Orhan')
INSERT [dbo].[Controller] ([controllerID], [personID], [residentID], [controllerName]) VALUES (821, 110, 111, N'Melisa Bayırcı')
INSERT [dbo].[Controller] ([controllerID], [personID], [residentID], [controllerName]) VALUES (824, 100, 101, N'Hakkı Alkan')
SET IDENTITY_INSERT [dbo].[Controller] OFF
GO
SET IDENTITY_INSERT [dbo].[Decisions] ON 

INSERT [dbo].[Decisions] ([DecisionID], [ApartmentID], [DecisionDescription], [VoteYes], [VoteNo], [DecisionDate], [isAccepted], [isFullOrMajority]) VALUES (17, 3, N'Due', 11, 0, CAST(N'2022-12-12' AS Date), N'YES', N'Majority')
INSERT [dbo].[Decisions] ([DecisionID], [ApartmentID], [DecisionDescription], [VoteYes], [VoteNo], [DecisionDate], [isAccepted], [isFullOrMajority]) VALUES (18, 4, N'Due', 8, 1, CAST(N'2022-06-06' AS Date), N'NO', N'Full')
INSERT [dbo].[Decisions] ([DecisionID], [ApartmentID], [DecisionDescription], [VoteYes], [VoteNo], [DecisionDate], [isAccepted], [isFullOrMajority]) VALUES (19, 5, N'Due', 4, 2, CAST(N'2022-06-06' AS Date), N'YES', N'Majority')
INSERT [dbo].[Decisions] ([DecisionID], [ApartmentID], [DecisionDescription], [VoteYes], [VoteNo], [DecisionDate], [isAccepted], [isFullOrMajority]) VALUES (20, 6, N'Due', 11, 0, CAST(N'2022-05-10' AS Date), N'YES', N'Full')
SET IDENTITY_INSERT [dbo].[Decisions] OFF
GO
SET IDENTITY_INSERT [dbo].[Expense] ON 

INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (1, 24, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (2, 18, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (3, 19, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (4, 20, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (5, 21, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (6, 22, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (7, 23, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (8, 24, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (9, 25, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (10, 26, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (11, 27, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (12, 28, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (13, 29, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (14, 30, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (15, 31, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (16, 32, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (17, 33, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (18, 34, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (19, 35, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (20, 36, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (21, 37, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (22, 38, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (23, 39, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (24, 40, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (25, 41, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (26, 42, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (27, 43, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (28, 44, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (29, 45, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (30, 46, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (31, 47, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (32, 48, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (33, 49, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (34, 50, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (35, 51, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (36, 52, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (37, 53, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (38, 54, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (39, 55, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (40, 56, 819)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (41, 18, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (42, 19, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (43, 20, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (44, 21, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (45, 22, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (46, 23, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (47, 24, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (48, 25, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (49, 26, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (50, 27, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (51, 28, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (52, 29, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (53, 30, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (54, 31, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (55, 32, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (56, 33, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (57, 34, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (58, 35, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (59, 36, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (60, 37, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (61, 38, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (62, 39, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (63, 40, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (64, 41, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (65, 42, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (66, 43, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (67, 44, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (68, 45, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (69, 46, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (70, 47, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (71, 48, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (72, 49, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (73, 50, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (74, 51, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (75, 52, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (76, 53, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (77, 54, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (78, 55, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (79, 56, 820)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (80, 18, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (81, 19, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (82, 20, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (83, 21, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (84, 22, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (85, 23, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (86, 24, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (87, 25, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (88, 26, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (89, 27, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (90, 28, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (91, 29, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (92, 30, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (93, 31, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (94, 32, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (95, 33, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (96, 34, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (97, 35, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (98, 36, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (99, 37, 821)
GO
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (100, 38, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (101, 39, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (102, 40, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (103, 41, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (104, 42, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (105, 43, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (106, 44, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (107, 45, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (108, 46, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (109, 47, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (110, 48, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (111, 49, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (112, 50, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (113, 51, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (114, 52, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (115, 53, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (116, 54, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (117, 55, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (118, 56, 821)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (119, 18, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (120, 19, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (121, 20, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (122, 21, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (123, 22, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (124, 23, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (125, 24, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (126, 25, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (127, 26, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (128, 27, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (129, 28, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (130, 29, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (131, 30, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (132, 31, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (133, 32, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (134, 33, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (135, 34, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (136, 35, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (137, 36, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (138, 37, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (139, 38, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (140, 39, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (141, 40, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (142, 41, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (143, 42, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (144, 43, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (145, 44, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (146, 45, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (147, 46, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (148, 47, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (149, 48, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (150, 49, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (151, 50, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (152, 51, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (153, 52, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (154, 53, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (155, 54, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (156, 55, 824)
INSERT [dbo].[Expense] ([ExpenseId], [ReceiptId], [ControllerId]) VALUES (157, 56, 824)
SET IDENTITY_INSERT [dbo].[Expense] OFF
GO
SET IDENTITY_INSERT [dbo].[Manager] ON 

INSERT [dbo].[Manager] ([managerID], [personID], [apartmentID], [residentID], [managerName]) VALUES (110, 78, 3, 79, N'Ahmet Yılmaz')
INSERT [dbo].[Manager] ([managerID], [personID], [apartmentID], [residentID], [managerName]) VALUES (111, 88, 4, 89, N'Fatih Yılmazer')
INSERT [dbo].[Manager] ([managerID], [personID], [apartmentID], [residentID], [managerName]) VALUES (112, 98, 5, 99, N'Ali Ceylan')
INSERT [dbo].[Manager] ([managerID], [personID], [apartmentID], [residentID], [managerName]) VALUES (113, 108, 6, 109, N'Gökhan Fırat')
INSERT [dbo].[Manager] ([managerID], [personID], [apartmentID], [residentID], [managerName]) VALUES (115, 79, 3, 80, N'Cicek Demirci')
INSERT [dbo].[Manager] ([managerID], [personID], [apartmentID], [residentID], [managerName]) VALUES (116, 89, 4, 90, N'İlkkan Yol')
INSERT [dbo].[Manager] ([managerID], [personID], [apartmentID], [residentID], [managerName]) VALUES (117, 99, 5, 100, N'Mehmet Uzman')
INSERT [dbo].[Manager] ([managerID], [personID], [apartmentID], [residentID], [managerName]) VALUES (118, 109, 6, 110, N'Ceyhun Gulselam')
SET IDENTITY_INSERT [dbo].[Manager] OFF
GO
SET IDENTITY_INSERT [dbo].[Message] ON 

INSERT [dbo].[Message] ([MessageID], [ManagerID], [ResidentID], [Message], [SendDate]) VALUES (1, 110, 84, N'Pay your due!', CAST(N'2022-10-11' AS Date))
INSERT [dbo].[Message] ([MessageID], [ManagerID], [ResidentID], [Message], [SendDate]) VALUES (2, 113, 116, N'Dont make noise!', CAST(N'2022-06-10' AS Date))
SET IDENTITY_INSERT [dbo].[Message] OFF
GO
SET IDENTITY_INSERT [dbo].[movedFlat] ON 

INSERT [dbo].[movedFlat] ([flatHistory], [oldFlatID], [newFlatID], [apartmentID], [enteranceDate], [leavingDate]) VALUES (1, 4, 5, 3, CAST(N'2021-12-12' AS Date), CAST(N'2022-12-12' AS Date))
INSERT [dbo].[movedFlat] ([flatHistory], [oldFlatID], [newFlatID], [apartmentID], [enteranceDate], [leavingDate]) VALUES (2, 4, 5, 3, CAST(N'2022-12-12' AS Date), CAST(N'2022-12-29' AS Date))
SET IDENTITY_INSERT [dbo].[movedFlat] OFF
GO
SET IDENTITY_INSERT [dbo].[Payment] ON 

INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (3, 18, 112)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (4, 19, 113)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (5, 20, 114)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (6, 21, 115)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (7, 22, 116)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (8, 23, 117)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (9, 25, 112)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (10, 26, 113)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (11, 27, 114)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (12, NULL, NULL)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (13, 18, 112)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (14, 19, 113)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (15, 20, 114)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (16, 21, 115)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (17, 22, 116)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (18, 23, 117)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (19, 24, NULL)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (20, 25, 112)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (21, 26, 113)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (22, 27, 114)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (23, 28, 83)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (24, 29, 84)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (25, 30, 85)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (26, 31, 87)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (27, 32, 88)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (28, 33, 101)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (29, 34, 102)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (30, 35, 103)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (31, 36, 104)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (32, 37, 105)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (33, 38, 106)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (34, 39, 107)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (35, 40, 108)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (36, 41, 100)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (37, 42, 104)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (38, 43, 105)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (39, 44, 106)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (40, 45, 107)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (41, 46, 79)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (42, 47, 80)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (43, 48, 81)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (44, 49, 83)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (45, 50, 84)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (46, 51, 87)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (47, 52, 88)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (48, 53, NULL)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (49, 54, 94)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (50, 18, 112)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (51, 19, 113)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (52, 20, 114)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (53, 21, 115)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (54, 22, 116)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (55, 23, 117)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (56, 24, NULL)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (57, 25, 112)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (58, 26, 113)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (59, 27, 114)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (60, 28, 83)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (61, 29, 84)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (62, 30, 85)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (63, 31, 87)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (64, 32, 88)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (65, 33, 101)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (66, 34, 102)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (67, 35, 103)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (68, 36, 104)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (69, 37, 105)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (70, 38, 106)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (71, 39, 107)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (72, 40, 108)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (73, 41, 100)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (74, 42, 104)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (75, 43, 105)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (76, 44, 106)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (77, 45, 107)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (78, 46, 79)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (79, 47, 80)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (80, 48, 81)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (81, 49, 83)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (82, 50, 84)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (83, 51, 87)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (84, 52, 88)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (85, 53, NULL)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (86, 54, 94)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (87, 55, 99)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (88, 56, 109)
INSERT [dbo].[Payment] ([paymentID], [receiptID], [residentID]) VALUES (89, 57, 97)
SET IDENTITY_INSERT [dbo].[Payment] OFF
GO
SET IDENTITY_INSERT [dbo].[Person] ON 

INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (78, 3, N'Ahmet Yılmaz', 1)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (79, 3, N'Cicek Demirci', 2)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (80, 3, N'Sadi Evren', 3)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (81, 3, N'Mert Urhan', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (82, 3, N'Hilal Kabanlı', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (83, 3, N'Umut Bilal Okur', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (84, 3, N'Mesut Bas', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (85, 3, N'Beyza Ayan', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (86, 3, N'Ezgi Ergül', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (87, 3, N'Fırat Subotay', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (88, 4, N'Fatih Yılmazer', 1)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (89, 4, N'İlkkan Yol', 2)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (90, 4, N'Hakkı Orhan', 3)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (91, 4, N'Sabahattin Ali', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (92, 4, N'Ahmet Aslan', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (93, 4, N'Pelin Sentürk', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (94, 4, N'Ferhat Yılmaz', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (95, 4, N'Murat Tutmaz', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (96, 4, N'Leyla Melis', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (97, 4, N'Tolga Oguzer', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (98, 5, N'Ali Ceylan', 1)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (99, 5, N'Mehmet Uzman', 2)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (100, 5, N'Hakkı Alkan', 3)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (101, 5, N'Orhan Denizci', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (102, 5, N'Cemil Dozer', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (103, 5, N'Yusuf Erdogan', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (104, 5, N'Dogucan Haspolat', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (105, 5, N'Umut Nayir', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (106, 5, N'Caner Erkin', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (107, 5, N'Arda Turan', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (108, 6, N'Gökhan Fırat', 1)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (109, 6, N'Ceyhun Gulselam', 2)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (110, 6, N'Melisa Bayırcı', 3)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (111, 6, N'Naci Univar', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (112, 6, N'Ezgi Ergül', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (113, 6, N'Ayse Hamyemez', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (114, 6, N'Dilan Okur', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (115, 6, N'Yunus Bayhan', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (116, 6, N'Recep Tayyip', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (117, 6, N'Kemal Davutoglu', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (118, 3, N'Ahmet Ferhat', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (119, 4, N'Yagmur Kelemci', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (120, 5, N'Mahmut Acar', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (121, 6, N'Tolga Engin', 4)
INSERT [dbo].[Person] ([personID], [apartmentID], [personName], [personStatus]) VALUES (122, 6, N'Mustafa Yeni', 4)
SET IDENTITY_INSERT [dbo].[Person] OFF
GO
INSERT [dbo].[PersonStatus] ([statusID], [statusName]) VALUES (1, N'Manager')
INSERT [dbo].[PersonStatus] ([statusID], [statusName]) VALUES (2, N'AsistantManger')
INSERT [dbo].[PersonStatus] ([statusID], [statusName]) VALUES (3, N'Controller')
INSERT [dbo].[PersonStatus] ([statusID], [statusName]) VALUES (4, N'Resident')
INSERT [dbo].[PersonStatus] ([statusID], [statusName]) VALUES (5, N'Serviceman')
INSERT [dbo].[PersonStatus] ([statusID], [statusName]) VALUES (6, N'Repairmen')
GO
SET IDENTITY_INSERT [dbo].[Receipt] ON 

INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (18, N'Receipt for repairment', 112, 750, CAST(N'2022-07-24' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (19, N'Receipt for repairment', 113, 750, CAST(N'2022-09-01' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (20, N'Receipt for repairment', 114, 750, CAST(N'2022-09-24' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (21, N'Receipt for repairment', 115, 750, CAST(N'2022-08-24' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (22, N'Receipt for repairment', 116, 750, CAST(N'2022-08-22' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (23, N'Receipt for repairment', 117, 750, CAST(N'2023-01-24' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (24, N'Reciept from Repairmen', NULL, 4500, CAST(N'2023-02-24' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (25, N'Due', 112, 300, CAST(N'2023-01-24' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (26, N'Due', 113, 300, CAST(N'2023-01-24' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (27, N'Due', 114, 300, CAST(N'2023-01-24' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (28, N'Due', 83, 250, CAST(N'2022-03-15' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (29, N'Due', 84, 250, CAST(N'2022-03-16' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (30, N'Due', 85, 250, CAST(N'2022-04-13' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (31, N'Due', 87, 250, CAST(N'2022-04-15' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (32, N'Due', 88, 250, CAST(N'2022-05-10' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (33, N'Big maintenance operation', 101, 1250, CAST(N'2022-11-10' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (34, N'Big maintenance operatio', 102, 1250, CAST(N'2022-11-12' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (35, N'Big maintenance operatio', 103, 1250, CAST(N'2022-11-15' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (36, N'Big maintenance operation', 104, 1250, CAST(N'2022-12-10' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (37, N'Big maintenance operatio', 105, 1250, CAST(N'2022-12-15' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (38, N'Big maintenance operatio', 106, 1250, CAST(N'2022-12-20' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (39, N'Big maintenance operation', 107, 1250, CAST(N'2023-01-10' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (40, N'Big maintenance operatio', 108, 1250, CAST(N'2023-01-15' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (41, N'Due', 100, 250, CAST(N'2022-12-10' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (42, N'Due', 104, 250, CAST(N'2022-12-15' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (43, N'Due', 105, 250, CAST(N'2022-12-21' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (44, N'Due', 106, 250, CAST(N'2023-01-15' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (45, N'Due', 107, 250, CAST(N'2022-07-24' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (46, N'Due', 79, 200, CAST(N'2023-01-24' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (47, N'Due', 80, 200, CAST(N'2023-01-24' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (48, N'Due', 81, 200, CAST(N'2023-01-24' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (49, N'Due', 83, 200, CAST(N'2023-01-24' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (50, N'Due', 84, 200, CAST(N'2023-01-24' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (51, N'Due', 87, 200, CAST(N'2023-01-24' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (52, N'Due', 88, 200, CAST(N'2023-01-24' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (53, N'Receipt from repairmen', NULL, 10000, CAST(N'2023-01-24' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (54, N'Due', 94, 300, CAST(N'2022-06-10' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (55, N'Manager Payment', 99, 5500, CAST(N'2022-10-10' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (56, N'Manager Payment', 109, 5500, CAST(N'2022-07-10' AS Date))
INSERT [dbo].[Receipt] ([ReceiptID], [ReceiptDescription], [ResidentID], [ReceiptAmount], [ReceiptTime]) VALUES (57, N'Due', 97, 260, CAST(N'2022-09-10' AS Date))
SET IDENTITY_INSERT [dbo].[Receipt] OFF
GO
SET IDENTITY_INSERT [dbo].[Resident] ON 

INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (79, 78, 3, N'Ahmet Yılmaz', N'+905311596784', 0, 1)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (80, 79, 3, N'Cicek Demirci', N'+905379451223', 1, 2)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (81, 80, 3, N'Sadi Evren', N'+90538156156', 1, 3)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (82, 81, 3, N'Mert Urhan', N'+905325323525', 0, 4)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (83, 82, 3, N'Hilal Kabanlı', N'+905326454656', 1, 4)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (84, 83, 3, N'Umut Bilal Okur', N'+905325687687', 1, 4)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (85, 84, 3, N'Mesut Bas', N'+905368768778', 1, 5)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (86, 85, 3, N'Beyza Ayan', N'+905654654645', 0, 5)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (87, 86, 3, N'Ezgi Ergül', N'+905656546565', 1, 6)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (88, 87, 3, N'Fırat Subotay', N'+905546546540', 1, 7)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (89, 88, 4, N'Fatih Yılmazer', N'+905325323525', 1, 1)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (90, 89, 4, N'İlkkan Yol', N'+905546546467', 0, 1)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (91, 90, 4, N'Hakkı Orhan', N'+905325323525', 1, 2)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (92, 91, 4, N'Sabahattin Ali', N'+905325564564', 1, 2)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (93, 92, 4, N'Ahmet Aslan', N'+905987987987', 1, 3)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (94, 93, 4, N'Pelin Sentürk', N'+905615665613', 1, 3)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (95, 94, 4, N'Ferhat Yılmaz', N'+905325323525', 1, 4)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (96, 95, 4, N'Murat Tutmaz', N'+905325323525', 1, 5)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (97, 96, 4, N'Leyla Melis', N'+905646546546', 1, 6)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (98, 97, 4, N'Tolga Oguzer', N'+905565555652', 0, 7)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (99, 98, 5, N'Ali Ceylan', N'+905456456465', 0, 1)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (100, 99, 5, N'Mehmet Uzman', N'+905325323525', 1, 2)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (101, 100, 5, N'Hakkı Alkan', N'+905646546545', 0, 3)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (102, 101, 5, N'Orhan Denizci', N'+905325323525', 1, 3)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (103, 102, 5, N'Cemil Dozer', N'+905984984561', 0, 4)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (104, 103, 5, N'Yusuf Erdogan', N'+905325323525', 1, 4)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (105, 104, 5, N'Dogucan Haspolat', N'+905325323525', 1, 5)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (106, 105, 5, N'Umut Nayir', N'+905325323525', 1, 6)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (107, 106, 5, N'Caner Erkin', N'+905532532532', 1, 7)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (108, 107, 5, N'Arda Turan', N'+905364364364', 0, 7)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (109, 108, 6, N'Gökhan Fırat', N'+905325323525', 0, 1)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (110, 109, 6, N'Ceyhun Gulselam', N'+905464364364', 1, 1)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (111, 110, 6, N'Melisa Bayırcı', N'+905325323525', 0, 2)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (112, 111, 6, N'Naci Univar', N'+905332432432', 1, 2)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (113, 112, 6, N'Ezgi Ergül', N'+905353253253', 1, 2)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (114, 113, 6, N'Ayse Hamyemez', N'+905324364343', 1, 3)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (115, 114, 6, N'Dilan Okur', N'+905325323525', 0, 3)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (116, 115, 6, N'Yunus Bayhan', N'+905364574755', 1, 4)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (117, 116, 6, N'Recep Tayyip', N'+905325323525', 0, 5)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (118, 117, 6, N'Kemal Davutoglu', N'+905365474754', 1, 6)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (119, 118, 3, N'Ahmet Ferhat', N'+90532960530', 1, 9)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (120, 119, 4, N'Yagmur Kelemci', N'', 1, 5)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (121, 120, 5, N'Mahmut Acar', N'', 1, 5)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (122, 121, 6, N'Tolga Engin', N'', 1, 9)
INSERT [dbo].[Resident] ([residentID], [personID], [apartmentID], [residentName], [residentPhoneNum], [paidFlag], [flatId]) VALUES (123, 122, 6, N'Mustafa Yeni', N'+9054373959385', 1, 9)
SET IDENTITY_INSERT [dbo].[Resident] OFF
GO
SET IDENTITY_INSERT [dbo].[Subscription] ON 

INSERT [dbo].[Subscription] ([SubscriptionID], [SubscriptionType], [managerID]) VALUES (1, N'Electricity(Elevator)', 110)
INSERT [dbo].[Subscription] ([SubscriptionID], [SubscriptionType], [managerID]) VALUES (2, N'Electricity(Other)', 110)
INSERT [dbo].[Subscription] ([SubscriptionID], [SubscriptionType], [managerID]) VALUES (3, N'Water Subscription', 110)
INSERT [dbo].[Subscription] ([SubscriptionID], [SubscriptionType], [managerID]) VALUES (4, N'Water Subscription', 113)
INSERT [dbo].[Subscription] ([SubscriptionID], [SubscriptionType], [managerID]) VALUES (5, N'Electricity(Elevator)', 116)
INSERT [dbo].[Subscription] ([SubscriptionID], [SubscriptionType], [managerID]) VALUES (6, N'Electricity(Other)', 117)
SET IDENTITY_INSERT [dbo].[Subscription] OFF
GO
/****** Object:  Index [UQ__Assistan__5F99AB33DFF0A8FD]    Script Date: 24.01.2023 14:13:41 ******/
ALTER TABLE [dbo].[AssistantManager] ADD UNIQUE NONCLUSTERED 
(
	[personID] ASC,
	[managerID] ASC,
	[residentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UQ__Controll__95D00CE9A8FB940D]    Script Date: 24.01.2023 14:13:41 ******/
ALTER TABLE [dbo].[Controller] ADD UNIQUE NONCLUSTERED 
(
	[personID] ASC,
	[residentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UQ__Manager__E14E47B0AABE135D]    Script Date: 24.01.2023 14:13:41 ******/
ALTER TABLE [dbo].[Manager] ADD UNIQUE NONCLUSTERED 
(
	[personID] ASC,
	[apartmentID] ASC,
	[residentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UQ__Resident__EC7D7D6CA13B758B]    Script Date: 24.01.2023 14:13:41 ******/
ALTER TABLE [dbo].[Resident] ADD UNIQUE NONCLUSTERED 
(
	[personID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[AssistantManager]  WITH CHECK ADD FOREIGN KEY([managerID])
REFERENCES [dbo].[Manager] ([managerID])
GO
ALTER TABLE [dbo].[AssistantManager]  WITH CHECK ADD FOREIGN KEY([personID])
REFERENCES [dbo].[Person] ([personID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[AssistantManager]  WITH CHECK ADD FOREIGN KEY([residentID])
REFERENCES [dbo].[Resident] ([residentID])
GO
ALTER TABLE [dbo].[Bill]  WITH CHECK ADD  CONSTRAINT [FK_Bill_Subscription] FOREIGN KEY([subscriptionID])
REFERENCES [dbo].[Subscription] ([SubscriptionID])
GO
ALTER TABLE [dbo].[Bill] CHECK CONSTRAINT [FK_Bill_Subscription]
GO
ALTER TABLE [dbo].[Controller]  WITH CHECK ADD FOREIGN KEY([personID])
REFERENCES [dbo].[Person] ([personID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Controller]  WITH CHECK ADD FOREIGN KEY([residentID])
REFERENCES [dbo].[Resident] ([residentID])
GO
ALTER TABLE [dbo].[Decisions]  WITH CHECK ADD  CONSTRAINT [FK_Decisions_Apartment] FOREIGN KEY([ApartmentID])
REFERENCES [dbo].[Apartment] ([apartmentID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Decisions] CHECK CONSTRAINT [FK_Decisions_Apartment]
GO
ALTER TABLE [dbo].[Expense]  WITH CHECK ADD  CONSTRAINT [FK_Expense_Controller] FOREIGN KEY([ControllerId])
REFERENCES [dbo].[Controller] ([controllerID])
GO
ALTER TABLE [dbo].[Expense] CHECK CONSTRAINT [FK_Expense_Controller]
GO
ALTER TABLE [dbo].[Expense]  WITH CHECK ADD  CONSTRAINT [FK_Expense_Reciept] FOREIGN KEY([ReceiptId])
REFERENCES [dbo].[Receipt] ([ReceiptID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Expense] CHECK CONSTRAINT [FK_Expense_Reciept]
GO
ALTER TABLE [dbo].[Manager]  WITH CHECK ADD FOREIGN KEY([apartmentID])
REFERENCES [dbo].[Apartment] ([apartmentID])
GO
ALTER TABLE [dbo].[Manager]  WITH CHECK ADD FOREIGN KEY([personID])
REFERENCES [dbo].[Person] ([personID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Manager]  WITH CHECK ADD FOREIGN KEY([residentID])
REFERENCES [dbo].[Resident] ([residentID])
GO
ALTER TABLE [dbo].[Message]  WITH CHECK ADD  CONSTRAINT [FK_Message_Manager] FOREIGN KEY([ManagerID])
REFERENCES [dbo].[Manager] ([managerID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Message] CHECK CONSTRAINT [FK_Message_Manager]
GO
ALTER TABLE [dbo].[Message]  WITH CHECK ADD  CONSTRAINT [FK_Message_Resident] FOREIGN KEY([ResidentID])
REFERENCES [dbo].[Resident] ([residentID])
GO
ALTER TABLE [dbo].[Message] CHECK CONSTRAINT [FK_Message_Resident]
GO
ALTER TABLE [dbo].[movedFlat]  WITH CHECK ADD  CONSTRAINT [FK_movedFlat_Apartment] FOREIGN KEY([apartmentID])
REFERENCES [dbo].[Apartment] ([apartmentID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[movedFlat] CHECK CONSTRAINT [FK_movedFlat_Apartment]
GO
ALTER TABLE [dbo].[Payment]  WITH CHECK ADD  CONSTRAINT [FK_Payment_Receipt] FOREIGN KEY([receiptID])
REFERENCES [dbo].[Receipt] ([ReceiptID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Payment] CHECK CONSTRAINT [FK_Payment_Receipt]
GO
ALTER TABLE [dbo].[Person]  WITH CHECK ADD FOREIGN KEY([apartmentID])
REFERENCES [dbo].[Apartment] ([apartmentID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Person]  WITH CHECK ADD  CONSTRAINT [FK_Person_PersonStatus] FOREIGN KEY([personStatus])
REFERENCES [dbo].[PersonStatus] ([statusID])
GO
ALTER TABLE [dbo].[Person] CHECK CONSTRAINT [FK_Person_PersonStatus]
GO
ALTER TABLE [dbo].[Receipt]  WITH CHECK ADD  CONSTRAINT [FK_Reciept_Resident] FOREIGN KEY([ResidentID])
REFERENCES [dbo].[Resident] ([residentID])
GO
ALTER TABLE [dbo].[Receipt] CHECK CONSTRAINT [FK_Reciept_Resident]
GO
ALTER TABLE [dbo].[Resident]  WITH CHECK ADD FOREIGN KEY([personID])
REFERENCES [dbo].[Person] ([personID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Subscription]  WITH CHECK ADD  CONSTRAINT [FK_Subscriptions_Manager] FOREIGN KEY([managerID])
REFERENCES [dbo].[Manager] ([managerID])
GO
ALTER TABLE [dbo].[Subscription] CHECK CONSTRAINT [FK_Subscriptions_Manager]
GO
ALTER TABLE [dbo].[Person]  WITH CHECK ADD CHECK  (([PersonStatus]>(0) AND [PersonStatus]<(5)))
GO
ALTER TABLE [dbo].[Resident]  WITH CHECK ADD CHECK  (([paidFlag]=(0) OR [paidFlag]=(1)))
GO
