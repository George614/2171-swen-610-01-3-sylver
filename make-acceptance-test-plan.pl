#!/usr/bin/perl
# -*- Perl -*-
#

#-----------------------------------------------------------------------
#
#  This script converts a Trello board export to the format needed for
#  the acceptance test plan.
#
#  The input is a tab separated file created by saving the exported
#  Trello board Excel file as a "Text (Tab delimited)" file. The output
#  is a tab delimited file that can be imported into Excel. The
#  acceptance criteria rows then get copied to the acceptance test plan
#  template.
#
#  There are command line options to redefine the default fields and
#  labels used for story title, checklist name, and acceptance criteria
#  label.
#
#-----------------------------------------------------------------------

use strict;

use Data::Dumper;
use File::Basename;

use constant FALSE => 0;
use constant TRUE => (!FALSE);

# For getopts processing of the command line
use Getopt::Std;
$Getopt::Std::STANDARD_HELP_VERSION = TRUE;

my $cmdOptionKeys = 'a:hi:n:t:v';
use constant SET_ACCEPTANCE_LABEL => 'a';
use constant SHOW_USAGE => 'h';
use constant SET_ITEM_COL => 'i';
use constant SET_NAME_COL => 'n';
use constant SET_TITLE_COL => 't';
use constant VERBOSE => 'v';

my %cmdOptions;
my $verbose = FALSE;

# These are the default heading labels for the columns of interest, and the
# label for an acceptance criteria checklist item.
my $titleHeader = "Title";
my $titleIndex = -1;
my $checklistNameHeader = "Checklists";
my $checklistNameIndex = -1;
my $checklistItemHeader = "Comments";
my $checklistItemIndex = -1;
my $acChecklistLabel = "Acceptance Criteria";

#-----------------------------------------------------------------------
#
#  Definition of subroutines
#
#-----------------------------------------------------------------------

# Print version and help information in the form needed by getopts
my $VERSION = 'v1.0 jrv';
my $scriptName = fileparse($0);
sub VERSION_MESSAGE() {
    print "$scriptName version: $VERSION\n";
}

sub HELP_MESSAGE()
{
    $! = 1;
    die 
"Usage:\
$scriptName [-hv] [-a acceptance-list]\
      [-i checklist-item-col] [-n checklist-name-col] [-t card-title-col]\
      [tabbed-trello-board-export]\
\
  -a\
  set name for acceptance criteria lists; default is\
  \"$acChecklistLabel\"\
\
  -h, --help\
  output this message\
\
  -i\
  set checklist item column number; default is column with heading\
  of \"$checklistItemHeader\"\
\
  -n\
  set checklist name column number; default is column with heading\
  of \"$checklistNameHeader\"\
\
  -t\
  set card title column number; default is column with heading of\
  \"$titleHeader\"\
\
  -v\
  generate verbose output\
\
  --version\
  output the version\
\
Column numbers start at 1.\n";
}


#-----------------------------------------------------------------------
#
#  Process command line arguments
#
#-----------------------------------------------------------------------

# Process any command line options
if(!getopts($cmdOptionKeys, \%cmdOptions)) {
  HELP_MESSAGE(); # will die there
}

# The SHOW_USAGE option will print a usage message
if($cmdOptions{+SHOW_USAGE}) {
  HELP_MESSAGE(); # will die there
}

# The VERBOSE option will track progress through the processing of comments
if($cmdOptions{+VERBOSE}) {
  $verbose = TRUE;
  print "Verbose mode is on.\n";
}

# The SET_ACCEPTANCE_LABEL  option will set the name of the checklist holding
# acceptance criteria
if($cmdOptions{+SET_ACCEPTANCE_LABEL}) {
    $acChecklistLabel = $cmdOptions{+SET_ACCEPTANCE_LABEL};
    if($verbose) {
  print("Acceptance criteria list name set to $acChecklistLabel.\n");
    }
}

# The SET_ITEM_COL  option will print a usage message
if($cmdOptions{+SET_ITEM_COL}) {
    $checklistItemIndex = $cmdOptions{+SET_ITEM_COL} - 1;
    if($verbose) {
  print("Checklist item index set to $checklistItemIndex.\n");
    }
}

# The SET_NAME_COL option will print a usage message
if($cmdOptions{+SET_NAME_COL}) {
    $checklistNameIndex = $cmdOptions{+SET_NAME_COL} - 1;
    if($verbose) {
  print("Checklist name index set to $checklistNameIndex.\n");
    }
}

# The SET_TITLE_COL option will print a usage message
if($cmdOptions{+SET_TITLE_COL}) {
    $titleIndex = $cmdOptions{+SET_TITLE_COL} - 1;
    if($verbose) {
  print("Card title index set to $titleIndex.\n");
    }
}

#-----------------------------------------------------------------------
#
#  Start processing Trello cards
#
#-----------------------------------------------------------------------

# If the columns of interest were not set on the command line, search
# the header row fields to find the columns: title, checklist name,
# checklist item.
my @fields;
(@fields) = split('\t',<>);

foreach my $index (0..$#fields) {
    if($fields[$index] eq $titleHeader) {
  $titleIndex = $index;
    }
    elsif($fields[$index] eq $checklistNameHeader) {
  $checklistNameIndex = $index;
    }
    elsif($fields[$index] eq $checklistItemHeader) {
  $checklistItemIndex = $index;
    }
}

if($titleIndex == -1) {
    $! = 2;
    die "Title column heading was not found.\n";
}
if($checklistNameIndex == -1) {
    $! = 2;
    die "Checklist name column heading was not found.\n";
}
if($checklistItemIndex == -1) {
    $! = 2;
    die "Checklist item column heading was not found.\n";
}

my $line = "";
my $currentTitle = "";
my $newTitle;

# Output the headers for the test plan worksheet
print "User Story\tAcceptance Criteria\tPass/Fail\tComments from the Tester\n";

while (<>) {
    s/\r//;   # handle line endings
    $line = $line . $_;

    # Handle embedded newlines by counting the number of quotes in the
    # assembled line. (In a scalar context, tr returns the number of
    # translations made.) If the number of quotes is even, assume the
    # line is complete and process it. Otherwise, grab the next input
    # line and tack it on the end.
    if((($line =~ tr/\"/\"/) % 2) == 0) {
  (@fields) = split('\t',$line);

  # Track the current title so that it can be output with its first
  # acceptance criteria, if any are defined.
  if($fields[$titleIndex] ne $currentTitle) {
      if($verbose) {
    print "New title found $fields[$titleIndex]\n";
      }
      $currentTitle = $fields[$titleIndex];
      $newTitle = TRUE;
  }

  # If this is an acceptance criteria then something gets output.
  if($fields[$checklistNameIndex] eq $acChecklistLabel) {
      if($verbose) {
    print "Acceptance criteria: $fields[$checklistItemIndex]\n";
      }     

      if($newTitle) {
    print "$fields[$titleIndex]";
    $newTitle = FALSE;
      }
      print "\t$fields[$checklistItemIndex]\n";
  }

  # The line was processed so setup to build the next complete one.
  $line = "";
    }
    else {
  if($verbose) {
      print "$.: line is continued\n";
  }
    }
}