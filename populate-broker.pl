#!/usr/bin/perl

system("cat pact-consumer/build/pact/Planner-Supplies.json| curl -v -XPUT -H 'Content-Type: application/json' --data \@- http://localhost:80/pacts/provider/Supplies/consumer/Planner/version/1.0.2");

my @other = (
  [Orders => Delivery],
  [Planner => Employees],
  [Shop => Orders],
  [Shop => Supplies],
);

foreach my $arr (@other) {
  my ($consumer, $provider) = @{$arr};
  print "$consumer => $provider\n";
  system("sed -e 's/Planner/$consumer/' -e 's/Supplies/$provider/' pact-consumer/build/pact/Planner-Supplies.json| curl -v -XPUT -H 'Content-Type: application/json' --data \@- http://localhost:80/pacts/provider/$provider/consumer/$consumer/version/1.0.0");
  sleep 60;
}

#echo '{"success": true, "providerApplicationVersion": "4.5.6", "buildUrl": "http://my-ci.org/build/3456"}' | curl -v -XPOST
